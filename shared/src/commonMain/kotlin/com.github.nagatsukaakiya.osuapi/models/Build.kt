package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Build(
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("display_version")
    val displayVersion: String,
    val id: Int,
    @SerialName("update_stream")
    val updateStream: UpdateStream? = null,
    val users: Int,
    val version: String? = null,
)
