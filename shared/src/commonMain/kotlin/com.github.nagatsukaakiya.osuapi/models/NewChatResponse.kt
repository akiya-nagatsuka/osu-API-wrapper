package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.Serializable

@Serializable
data class NewChatResponse(
    val channel: ChatChannel,
    val message: ChatMessage,
)
