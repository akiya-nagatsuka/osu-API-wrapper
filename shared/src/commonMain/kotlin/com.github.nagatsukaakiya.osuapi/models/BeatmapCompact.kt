package com.github.nagatsukaakiya.osuapi.models

import com.github.nagatsukaakiya.osuapi.ranking.requests.GameMode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeatmapCompact(
    @SerialName("beatmapset_id")
    val beatmapsetId: Int,
    @SerialName("difficulty_rating")
    val difficultyRating: Float,
    val id: Int,
    val mode: GameMode,
    /** See Rank status for list of possible values. */
    val status: String,
    @SerialName("total_length")
    val totalLength: Int,
    @SerialName("user_id")
    val userId: Int,
    val version: String,
    @SerialName("max_combo")
    val maxCombo: Int? = null,
)
