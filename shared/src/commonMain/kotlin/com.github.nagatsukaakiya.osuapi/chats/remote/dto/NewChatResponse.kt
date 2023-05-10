package com.github.nagatsukaakiya.osuapi.chats.remote.dto

import com.github.nagatsukaakiya.osuapi.models.ChatChannel
import com.github.nagatsukaakiya.osuapi.models.ChatMessage
import kotlinx.serialization.Serializable

@Serializable
data class NewChatResponse(
    val channel: ChatChannel,
    val message: ChatMessage,
)