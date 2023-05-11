package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
enum class GameMode {
    @SerialName("osu")
    Standard,
    @SerialName("mania")
    Mania,
    @SerialName("fruits")
    Catch,
    @SerialName("taiko")
    Taiko;

    internal fun getValue(): String = Json.decodeFromJsonElement(Json.encodeToJsonElement(this))
}