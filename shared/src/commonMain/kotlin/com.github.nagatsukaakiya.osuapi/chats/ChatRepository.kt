package com.github.nagatsukaakiya.osuapi.chats

import com.github.nagatsukaakiya.osuapi.chats.remote.ChatRemoteDataProvider
import com.github.nagatsukaakiya.osuapi.models.NewChatResponse

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
