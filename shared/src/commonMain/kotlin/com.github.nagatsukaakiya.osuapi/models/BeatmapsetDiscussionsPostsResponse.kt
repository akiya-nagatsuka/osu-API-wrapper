package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeatmapsetDiscussionsPostsResponse(
    val beatmapset: BeatmapsetCompact,
    @SerialName("cursor_string")
    val cursorString: String,
    val posts: List<BeatmapsetDiscussionPost>,
    val users: UserCompact,
)
