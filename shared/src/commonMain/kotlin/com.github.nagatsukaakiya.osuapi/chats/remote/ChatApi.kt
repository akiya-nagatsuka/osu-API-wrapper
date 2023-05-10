package com.github.nagatsukaakiya.osuapi.chats.remote

import com.github.nagatsukaakiya.osuapi.chats.remote.dto.NewChatRequest
import com.github.nagatsukaakiya.osuapi.chats.remote.dto.NewChatResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

interface ChatApi {
    suspend fun newChat(token: String, userId: Int, message: String): NewChatResponse

    suspend fun getMessages(token: String): List<String>
}

internal class FakeChatImpl : ChatApi {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json(DefaultJson) { ignoreUnknownKeys = true })
            expectSuccess = true
//            HttpResponseValidator {
//                validateResponse { response ->
//                    val error: Error = response.body()
//                    throw CustomResponseException(response, "Message: ${error.error}")
//                }
//            }
        }
    }
    companion object {
        private const val newChat = "https://osu.ppy.sh/api/v2/chat/new"
    }

    override suspend fun newChat(token: String, userId: Int, message: String): NewChatResponse =
        client.post(newChat) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token)
            }
            setBody(NewChatRequest(targetId = userId, message = message))
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body()

    override suspend fun getMessages(token: String) = listOf("Hi!")
}

@Serializable
data class Error(val error: String)

class CustomResponseException(response: HttpResponse, cachedResponseText: String) :
    ResponseException(response, cachedResponseText) {
    override val message: String = "Custom server error: ${response.call.request.url}. " +
            "Status: ${response.status}. Text: \"$cachedResponseText\""
}
