package com.example.myapplication.chat.repository.remote

interface ChatApi {
    suspend fun getMessages(token: String): List<String>
}

class FakeChatApi : ChatApi {
    override suspend fun getMessages(token: String) = listOf("Hi!")
}
