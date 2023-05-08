package com.github.nagatsukaakiya.osuapi.auth

import com.github.nagatsukaakiya.osuapi.clientId
import com.github.nagatsukaakiya.osuapi.redirectUrl
import com.github.nagatsukaakiya.osuapi.redirectUrlFormatted
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

interface TokenProvider {
    val isAuthenticated: Boolean
    suspend fun authorise(isForce: Boolean = false)
    fun setCode(code: String)
    suspend fun getTokenByCode(): String

    suspend fun getTokenByRefresh(tokenScope: TokenScope? = null): String
}

internal class TokenProviderImpl(private val localProvider: TokenLocalProvider) : TokenProvider {
    companion object {
        private const val tokenEndpoint = "https://osu.ppy.sh/oauth/token"
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    override val isAuthenticated: Boolean
        get() = localProvider.getRefreshToken() != null

    override suspend fun authorise(isForce: Boolean) {
        if (!isForce && localProvider.getCode() != null) return
        webAuthenticate("https://osu.ppy.sh/oauth/authorize?client_id=$clientId&redirect_uri=$redirectUrlFormatted&response_type=code&scope=chat.write+public")
    }

    override fun setCode(code: String) = localProvider.putCode(code)

    override suspend fun getTokenByCode() = getToken(GrantType.AuthorizationCode, localProvider.getCode())

    override suspend fun getTokenByRefresh(tokenScope: TokenScope?) = getToken(GrantType.RefreshToken, null, tokenScope)

    private suspend fun getToken(type: GrantType, code: String? = null, tokenScope: TokenScope? = null): String {
        if (type == GrantType.AuthorizationCode && code.isNullOrEmpty()) {
            error("No code")
        }
        if (type == GrantType.RefreshToken && code != null) {
            error("Requesting by refresh token and code")
        }
        val refreshToken = localProvider.getRefreshToken()
        if (type == GrantType.RefreshToken && refreshToken.isNullOrEmpty()) {
            getTokenByCode()
        }
        val response: HttpResponse = client.post(tokenEndpoint) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
            }
            when (type) {
                GrantType.AuthorizationCode -> {
                    setBody(TokenRequest(grantType = type, code = code, redirectUri = redirectUrl))
                }
                GrantType.RefreshToken -> {
                    setBody(TokenRequest(grantType = type, scope = tokenScope, refreshToken = refreshToken))
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
