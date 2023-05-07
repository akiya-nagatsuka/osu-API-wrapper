package com.github.nagatsukaakiya.osuapi.repository.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewChatRequest(
    @SerialName("target_id")
    val targetId: Int,
    @SerialName("message")
    val message: String,
    @SerialName("is_action")
    val isAction: Boolean = false,
    @SerialName("uuid")
    val uuid: String? = null
)
