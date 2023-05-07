package com.github.nagatsukaakiya.osuapi.beatmaps.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class BeatmapUserScore(
    val position: Int,
    val score: Score,
)

@Serializable
data class Score(
    val id: String,
    val best_id: String,
    val user_id: String,
    val accuracy: String,
    val mods: String,
    val score: String,
    val max_combo: String,
    val perfect: String,

    @SerialName("statistics.count_50")
    val statistics_count_50: String,

    @SerialName("statistics.count_100")
    val statistics_count_100: String,

    @SerialName("statistics.count_300")
    val statistics_count_300: String,

    @SerialName("statistics.count_geki")
    val statistics_count_geki: String,

    @SerialName("statistics.count_katu")
    val statistics_count_katu: String,

    @SerialName("statistics.count_miss")
    val statistics_count_miss: String,
    val passed: Boolean,
    val pp: String,
    val rank: String,
    val created_at: String,
    val mode: String,
    val mode_int: String,
    val replay: String,
)
