package com.example.myapplication.auth

import com.example.myapplication.clientId
import com.example.myapplication.redirectUrlFormatted
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

interface TokenProvider {
    suspend fun getToken(code: String): String
    suspend fun authorise()
}

expect fun webAuthenticate(url: String)

internal class TokenProviderImpl : TokenProvider {
    companion object {
        private const val tokenEndpoint = "https://osu.ppy.sh/oauth/token"
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    override suspend fun authorise() {
        webAuthenticate("https://osu.ppy.sh/oauth/authorize?client_id=$clientId&redirect_uri=$redirectUrlFormatted&response_type=code")
    }

    override suspend fun getToken(code: String): String {
        val response: HttpResponse = client.post(tokenEndpoint) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
            }
            setBody(TokenRequest(code = code))
        }
        val tokenResponse: TokenResponse = response.body()
        return tokenResponse.accessToken
    }
}
