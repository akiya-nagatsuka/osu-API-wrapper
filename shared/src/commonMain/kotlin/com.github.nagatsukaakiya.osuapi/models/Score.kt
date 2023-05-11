package com.github.nagatsukaakiya.osuapi.models

import com.github.nagatsukaakiya.osuapi.ranking.requests.GameMode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Score(
    val id: Long,
    @SerialName("best_id")
    val bestId: Long,
    @SerialName("user_id")
    val userId: Int,
    val accuracy: Float,
    val mods: List<JsonElement>,
    val score: Int,
    @SerialName("max_combo")
    val maxCombo: Int,
    val perfect: Boolean,
    val statistics: Statistics,
    val passed: Boolean,
    val pp: Float,
    val rank: String,
    @SerialName("created_at")
    val createdAt: String,
    val mode: GameMode,
    @SerialName("mode_int")
    val modeInt: Int,
    val replay: Boolean,
)
