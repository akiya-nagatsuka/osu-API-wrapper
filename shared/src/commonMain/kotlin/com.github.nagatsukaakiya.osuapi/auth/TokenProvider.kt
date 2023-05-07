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
    suspend fun getTokenByCode(): String

    suspend fun getTokenByRefresh(tokenScope: TokenScope? = null): String
    suspend fun authorise()
    var code: String
}

class TokenProviderImpl : TokenProvider {
    companion object {
        private const val tokenEndpoint = "https://osu.ppy.sh/oauth/token"
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    override var code = ""

    private var refreshToken: String = ""

    override suspend fun authorise() {
        webAuthenticate("https://osu.ppy.sh/oauth/authorize?client_id=$clientId&redirect_uri=$redirectUrlFormatted&response_type=code&scope=chat.write+public")
    }

    override suspend fun getTokenByCode() = getToken(GrantType.AuthorizationCode, code)

    override suspend fun getTokenByRefresh(tokenScope: TokenScope?) = getToken(GrantType.RefreshToken, null, tokenScope)

    private suspend fun getToken(type: GrantType, code: String? = null, tokenScope: TokenScope? = null): String {
        if (type == GrantType.AuthorizationCode && code.isNullOrEmpty()) {
            error("No code")
        }
        if (type == GrantType.RefreshToken && code != null) {
            error("Requesting by refresh token and code")
        }
        if (type == GrantType.RefreshToken && refreshToken.isEmpty()) {
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
        tokenResponse.refreshToken?.let { refreshToken = it }
        return tokenResponse.accessToken
    }
}
