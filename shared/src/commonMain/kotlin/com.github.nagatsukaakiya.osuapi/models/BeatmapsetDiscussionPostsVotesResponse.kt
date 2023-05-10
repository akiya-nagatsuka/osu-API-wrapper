package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeatmapsetDiscussionPostsVotesResponse(
    @SerialName("cursor_string")
    val cursorString: String,
    val discussion: BeatmapsetDiscussion,
    val users: UserCompact,
    val votes: List<BeatmapsetDiscussionVote>,
)
