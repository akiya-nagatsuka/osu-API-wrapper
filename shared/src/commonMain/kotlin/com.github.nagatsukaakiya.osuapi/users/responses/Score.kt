package com.github.nagatsukaakiya.osuapi.users.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Score(
    val id: JsonElement,
    @SerialName("best_id")
    val bestId: JsonElement,
    @SerialName("user_id")
    val userId: JsonElement,
    val accuracy: JsonElement,
    val mods: JsonElement,
    val score: JsonElement,
    @SerialName("max_combo")
    val maxCombo: JsonElement,
    val perfect: JsonElement,
    val statistics: Statistics,
    val passed: Boolean,
    val pp: JsonElement,
    val rank: JsonElement,
    @SerialName("created_at")
    val createdAt: JsonElement,
    val mode: JsonElement,
    @SerialName("mode_int")
    val modeInt: JsonElement,
    val replay: JsonElement,
)

@Serializable
data class Statistics(
    @SerialName("count_50")
    val count50: JsonElement,
    @SerialName("count_100")
    val count100: JsonElement,
    @SerialName("count_300")
    val count300: JsonElement,
    @SerialName("count_geki")
    val countGeki: JsonElement,
    @SerialName("count_katu")
    val countKatu: JsonElement,
    @SerialName("count_miss")
    val countMiss: JsonElement,
)
