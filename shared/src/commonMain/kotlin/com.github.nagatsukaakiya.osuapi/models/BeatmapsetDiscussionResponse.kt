package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeatmapsetDiscussionResponse(
    /** List of beatmaps associated with the discussions returned. */
    val beatmaps: Beatmap,
    @SerialName("cursor_string")
    val cursorString: String,
    /** List of discussions according to sort order. */
    val discussions: List<BeatmapsetDiscussion>,
    /** Additional discussions related to discussions. */
    @SerialName("included_discussions")
    val includedDiscussions: List<BeatmapsetDiscussion>,
    /** Maximum number of blocks allowed in a review. */
    @SerialName("reviews_config")
    val reviewsConfig: ReviewsConfig,
    /** List of users associated with the discussions returned. */
    val users: List<UserCompact>,
)

@Serializable
data class ReviewsConfig(
    @SerialName("max_blocks")
    val maxBlocks: Int,
)
