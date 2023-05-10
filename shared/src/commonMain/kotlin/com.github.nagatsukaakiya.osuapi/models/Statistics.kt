package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

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
