package com.github.nagatsukaakiya.osuapi.repository.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ChatMessage(
    @SerialName("channel_id")
    val channelId: Int,
    val content: String,
    @SerialName("is_action")
    val isAction: Boolean,
    @SerialName("message_id")
    val messageId: Long,
    @SerialName("sender_id")
    val senderId: Int,
    val timestamp: String,
    val type: String,
    val uuid: String? = null,
    val sender: JsonObject?
)