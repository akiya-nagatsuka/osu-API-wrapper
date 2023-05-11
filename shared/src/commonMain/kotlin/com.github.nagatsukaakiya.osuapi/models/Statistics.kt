package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Statistics(
    @SerialName("count_50")
    val count50: Int,
    @SerialName("count_100")
    val count100: Int,
    @SerialName("count_300")
    val count300: Int,
    @SerialName("count_geki")
    val countGeki: Int,
    @SerialName("count_katu")
    val countKatu: Int,
    @SerialName("count_miss")
    val countMiss: Int,
)
