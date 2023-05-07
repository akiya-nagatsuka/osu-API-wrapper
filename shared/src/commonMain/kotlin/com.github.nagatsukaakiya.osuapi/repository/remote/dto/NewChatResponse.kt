package com.github.nagatsukaakiya.osuapi.repository.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewChatResponse(
    val channel: ChatChannel,
    val message: ChatMessage,
)