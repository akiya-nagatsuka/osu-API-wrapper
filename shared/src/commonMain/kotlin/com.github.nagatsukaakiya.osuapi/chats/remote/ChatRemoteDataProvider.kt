package com.github.nagatsukaakiya.osuapi.chats.remote

import com.github.nagatsukaakiya.osuapi.auth.TokenProvider
import com.github.nagatsukaakiya.osuapi.models.NewChatResponse

interface ChatRemoteDataProvider {
    suspend fun newChat(userId: Int, message: String): NewChatResponse
    suspend fun fetchMessages(): List<String>
}

internal class ChatRemoteDataProviderImpl(
    private val tokenProvider: TokenProvider,
    private val chatApi: ChatApi,
): ChatRemoteDataProvider {
    override suspend fun newChat(userId: Int, message: String): NewChatResponse {
        val token = tokenProvider.getTokenByCode()
        return chatApi.newChat(token, userId, message)
    }
    override suspend fun fetchMessages(): List<String> {
        val token = tokenProvider.getTokenByCode()
        return chatApi.getMessages(token)
    }
}
