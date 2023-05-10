package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Event(
    @SerialName("created_at")
    val createdAt: String,
    val id: Int,
    val type: JsonElement, // EventType // TODO Investigate
    val url: String,

    // For beatmaps and beatmapsets
    val title: String? = null,

    // For user
    val username: String? = null,
    // Only for usernameChange event. (nullable)
    val previousUsername: String? = null,
)
