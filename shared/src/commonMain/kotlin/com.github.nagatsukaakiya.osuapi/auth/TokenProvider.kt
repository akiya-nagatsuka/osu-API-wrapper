package com.github.nagatsukaakiya.osuapi.auth

import com.github.nagatsukaakiya.osuapi.clientId
import com.github.nagatsukaakiya.osuapi.redirectUrlFormatted
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

interface TokenProvider {
    suspend fun getToken(tokenScope: TokenScope? = null): String
    suspend fun getToken(code: String, tokenScope: TokenScope? = null): String
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

    override suspend fun authorise() {
        webAuthenticate("https://osu.ppy.sh/oauth/authorize?client_id=$clientId&redirect_uri=$redirectUrlFormatted&response_type=code&scope=chat.write")
    }

    override suspend fun getToken(tokenScope: TokenScope?) = getToken(code, tokenScope)

    override suspend fun getToken(code: String, tokenScope: TokenScope?): String {
        if (code.isEmpty()) {
            authorise()
            return ""
        }
        val response: HttpResponse = client.post(tokenEndpoint) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
            }
            setBody(TokenRequest(code = code, scope = tokenScope))
        }
        val tokenResponse: TokenResponse = response.body()
        return tokenResponse.accessToken
    }
}
