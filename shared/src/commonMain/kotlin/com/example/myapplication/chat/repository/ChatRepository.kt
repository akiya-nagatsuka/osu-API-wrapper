package com.example.myapplication.chat.repository

import com.example.myapplication.chat.repository.remote.ChatRemoteDataProvider
import com.example.myapplication.chat.repository.remote.dto.NewChatResponse

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
