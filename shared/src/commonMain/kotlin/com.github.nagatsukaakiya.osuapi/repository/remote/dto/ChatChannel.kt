package com.github.nagatsukaakiya.osuapi.repository.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ChatChannel(
    @SerialName("channel_id")
    val channelId: Int,
    val name: String,
    val description: String?,
    val icon: String?,
    val type: ChannelType,
    val moderated: Boolean,
    val uuid: String?,
    @SerialName("current_user_attributes")
    val currentUserAttributes: JsonObject? = null,
    @SerialName("last_message_id")
    val lastMessageId: Long?,
    @SerialName("recent_messages")
    val recentMessages: List<JsonObject>? = null,
    @SerialName("users")
    val users: List<Int>?,
)

enum class ChannelType {
    PUBLIC, PRIVATE, MULTIPLAYER, SPECTATOR, PM, GROUP, ANNOUNCE
}

// TODO
data class CurrentUserAttributes(
    val canDestroy: Boolean
)