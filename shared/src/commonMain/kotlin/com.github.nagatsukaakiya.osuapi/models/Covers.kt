package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Covers(
    val cover: String,
    @SerialName("cover@2x")
    val cover2x: String,
    val card: String,
    @SerialName("card@2x")
    val card2x: String,
    val list: String,
    @SerialName("list@2x")
    val list2x: String,
    val slimcover: String,
    @SerialName("slimcover@2x")
    val slimcover2x: String,
)
