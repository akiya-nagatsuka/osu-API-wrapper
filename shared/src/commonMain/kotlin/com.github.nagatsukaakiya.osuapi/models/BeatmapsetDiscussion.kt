package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class BeatmapsetDiscussion(
    val beatmap: BeatmapCompact?,
    @SerialName("beatmap_id")
    val beatmapId: Int?,
    val beatmapset: BeatmapsetCompact?,
    @SerialName("beatmapset_id")
    val beatmapsetId: Int,
    @SerialName("can_be_resolved")
    val canBeResolved: Boolean,
    @SerialName("can_grant_kudosu")
    val canGrantKudosu: Boolean,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("current_user_attributes")
    val currentUserAttributes: JsonElement, // CurrentUserAttributes
    @SerialName("deleted_at")
    val deletedAt: String?,
    @SerialName("deleted_by_id")
    val deletedById: Int?,
    val id: Int,
    @SerialName("kudosu_denied")
    val kudosuDenied: Boolean,
    @SerialName("last_post_at")
    val lastPostAt: String,
    @SerialName("message_type")
    val messageType: String, // MessageType
    @SerialName("parent_id")
    val parentId: Int?,
    val posts: List<BeatmapsetDiscussionPost>?,
    val resolved: Boolean,
    @SerialName("starting_post")
    val startingPost: BeatmapsetDiscussionPost?,
    val timestamp: Int?,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("user_id")
    val userId: Int,
)
