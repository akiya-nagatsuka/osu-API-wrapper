package com.github.nagatsukaakiya.osuapi.repository

import com.github.nagatsukaakiya.osuapi.repository.remote.ChatRemoteDataProvider
import com.github.nagatsukaakiya.osuapi.repository.remote.dto.NewChatResponse

interface ChatRepository {
    suspend fun newChat(userId: Int, message: String): NewChatResponse
    suspend fun fetchMessages(): List<String>
}

internal class ChatRepositoryImpl(
    private val remoteDataProvider: ChatRemoteDataProvider
): ChatRepository {
    override suspend fun newChat(userId: Int, message: String) =
        remoteDataProvider.newChat(userId, message)
    override suspend fun fetchMessages() = remoteDataProvider.fetchMessages()
}
