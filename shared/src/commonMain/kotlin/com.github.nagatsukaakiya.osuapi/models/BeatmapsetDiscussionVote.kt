package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeatmapsetDiscussionVote(
    @SerialName("beatmapset_discussion_id")
    val beatmapsetDiscussionId: Int,
    @SerialName("created_at")
    val createdAt: String,
    val id: Int,
    val score: Int,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("user_id")
    val userId: Int,
)
