package com.example.myapplication.chat.repository

import com.example.myapplication.chat.repository.remote.ChatRemoteDataProvider

interface ChatRepository {
    suspend fun fetchMessages(): List<String>
}

internal class ChatRepositoryImpl(
    private val remoteDataProvider: ChatRemoteDataProvider
): ChatRepository {
    override suspend fun fetchMessages() = remoteDataProvider.fetchMessages()
}
