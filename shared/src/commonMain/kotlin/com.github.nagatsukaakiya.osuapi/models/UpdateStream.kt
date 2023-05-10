package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateStream(
    @SerialName("display_name")
    val displayName: String,
    val id: Int,
    @SerialName("is_featured")
    val isFeatured: Boolean,
    val name: String,
)
