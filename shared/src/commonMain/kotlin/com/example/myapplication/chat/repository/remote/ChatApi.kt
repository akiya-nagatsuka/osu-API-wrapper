package com.example.myapplication.chat.repository.remote

import com.example.myapplication.auth.TokenRequest
import com.example.myapplication.auth.TokenResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

interface ChatApi {
    suspend fun getToken(code: String): String
    suspend fun getMessages(token: String): List<String>
}

class FakeChatApi : ChatApi {
    companion object {
        private const val tokenEndpoint = "https://osu.ppy.sh/oauth/token"
    }
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
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

    override suspend fun getMessages(token: String) = listOf("Hi!")
}
