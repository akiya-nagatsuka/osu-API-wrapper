package com.github.nagatsukaakiya.osuapi.auth

import com.github.nagatsukaakiya.osuapi.redirectUrl
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

interface TokenProvider {
    val isAuthenticated: Boolean
    fun setCredentials(clientId: String, clientSecret: String)
    suspend fun authorise(
        redirectUri: String,
        scope: List<String> = emptyList(),
        isForce: Boolean = false,
    )
    fun setCode(code: String)
    suspend fun getTokenByCode(): String
    suspend fun getTokenByRefresh(tokenScope: TokenScope? = null): String
}

internal class TokenProviderImpl(private val localProvider: TokenLocalProvider) : TokenProvider {
    companion object {
        private const val tokenEndpoint = "https://osu.ppy.sh/oauth/token"
        private const val authorize = "https://osu.ppy.sh/oauth/authorize"
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    private var clientId: String = ""
    private var clientSecret: String = ""

    override val isAuthenticated: Boolean
        get() = localProvider.getRefreshToken() != null

    override fun setCredentials(clientId: String, clientSecret: String) {
        this.clientId = clientId
        this.clientSecret = clientSecret
    }

    override suspend fun authorise(
        redirectUri: String,
        scope: List<String>,
        isForce: Boolean,
    ) {
        check(clientId.isNotBlank()) { "Call setCredentials to provide clientId" }
        if (!isForce && localProvider.getCode() != null) return

        val scopeFormatted = scope.joinToString(separator = "+")
        val params = "client_id=$clientId&redirect_uri=$redirectUri&response_type=code".let {
            if (scope.isNotEmpty()) "$it&scope=$scopeFormatted" else it
        }
        webAuthenticate("$authorize?$params")
    }

    override fun setCode(code: String) = localProvider.putCode(code)

    override suspend fun getTokenByCode() = getToken(GrantType.AuthorizationCode, localProvider.getCode())

    override suspend fun getTokenByRefresh(tokenScope: TokenScope?) = getToken(GrantType.RefreshToken, null, tokenScope)

    private suspend fun getToken(type: GrantType, code: String? = null, tokenScope: TokenScope? = null): String {
        check(clientId.isNotBlank()) { "Call setCredentials to provide clientId" }
        check(clientSecret.isNotBlank()) { "Call setCredentials to provide clientSecret" }

        check(type != GrantType.AuthorizationCode || !code.isNullOrEmpty()) { "No code" }
        check(type != GrantType.RefreshToken || code == null) { "Requesting by refresh token and code" }

        val refreshToken = localProvider.getRefreshToken().let {
            if (type == GrantType.RefreshToken && it == null) {
                getTokenByCode()
                localProvider.getRefreshToken()
            } else it
        }
        val response: HttpResponse = client.post(tokenEndpoint) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
            }
            when (type) {
                GrantType.AuthorizationCode -> {
                    setBody(
                        TokenRequest(
                            clientId = clientId,
                            clientSecret = clientSecret,
                            grantType = type,
                            code = code,
                            redirectUri = redirectUrl,
                        )
                    )
                }
                GrantType.RefreshToken -> {
                    setBody(
                        TokenRequest(
                            clientId = clientId,
                            clientSecret = clientSecret,
                            grantType = type,
                            scope = tokenScope,
                            refreshToken = refreshToken,
                        )
                    )
                }
            }
        }
        val tokenResponse: TokenResponse = response.body()
        tokenResponse.refreshToken?.let {
            localProvider.putRefreshToken(it)
        }
        return tokenResponse.accessToken
    }
}
