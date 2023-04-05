package com.example.myapplication.chat.repository.remote

import com.example.myapplication.auth.TokenProvider

interface ChatRemoteDataProvider {
    suspend fun fetchMessages(): List<String>
}

internal class ChatRemoteDataProviderImpl(
    private val tokenProvider: TokenProvider,
    private val chatApi: ChatApi,
): ChatRemoteDataProvider {
    override suspend fun fetchMessages(): List<String> {
        val token = tokenProvider.getToken("")
        return chatApi.getMessages(token)
    }
}
