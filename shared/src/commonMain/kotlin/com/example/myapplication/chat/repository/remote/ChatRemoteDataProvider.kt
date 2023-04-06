package com.example.myapplication.chat.repository.remote

import com.example.myapplication.auth.TokenProvider
import com.example.myapplication.chat.repository.remote.dto.NewChatResponse

interface ChatRemoteDataProvider {
    suspend fun newChat(userId: Int, message: String): NewChatResponse
    suspend fun fetchMessages(): List<String>
}

internal class ChatRemoteDataProviderImpl(
    private val tokenProvider: TokenProvider,
    private val chatApi: ChatApi,
): ChatRemoteDataProvider {
    override suspend fun newChat(userId: Int, message: String): NewChatResponse {
        val token = tokenProvider.getToken()
        return chatApi.newChat(token, userId, message)
    }
    override suspend fun fetchMessages(): List<String> {
        val token = tokenProvider.getToken()
        return chatApi.getMessages(token)
    }
}
