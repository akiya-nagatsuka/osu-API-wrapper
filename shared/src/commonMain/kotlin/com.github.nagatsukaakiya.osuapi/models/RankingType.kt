package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
enum class RankingType {
    @SerialName("charts")
    Spotlight,
    @SerialName("country")
    Country,
    @SerialName("performance")
    Performance,
    @SerialName("score")
    Score;

    internal fun getValue(): String = Json.decodeFromJsonElement(Json.encodeToJsonElement(this))
}
