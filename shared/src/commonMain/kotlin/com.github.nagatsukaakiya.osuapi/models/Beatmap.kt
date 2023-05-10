package com.github.nagatsukaakiya.osuapi.models

import com.github.nagatsukaakiya.osuapi.ranking.requests.GameMode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Beatmap(
    val accuracy: Float,
    val ar: Float,
    val bpm: Float?,
    val convert: Boolean,
    @SerialName("count_circles")
    val countCircles: Int,
    @SerialName("count_sliders")
    val countSliders: Int,
    @SerialName("count_spinners")
    val countSpinners: Int,
    val cs: Float,
    @SerialName("deleted_at")
    val deletedAt: String?,
    val drain: Float,
    @SerialName("hit_length")
    val hitLength: Int,
    @SerialName("is_scoreable")
    val isScoreable: Boolean,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("mode_int")
    val modeInt: Int,
    val passcount: Int,
    val playcount: Int,
    /** See Rank status for list of possible values. */
    val ranked: Int,
    val url: String,

    // From BeatmapCompact
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
