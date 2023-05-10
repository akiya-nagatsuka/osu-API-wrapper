package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeatmapsetDiscussionPost(
    @SerialName("beatmapset_discussion_id")
    val beatmapsetDiscussionId: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("deleted_at")
    val deletedAt: String?,
    @SerialName("deleted_by_id")
    val deletedById: Int,
    val id: Int,
    @SerialName("last_editor_id")
    val lastEditorId: Int?,
    val message: String,
    val system: Boolean,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("user_id")
    val userId: Int,
)
