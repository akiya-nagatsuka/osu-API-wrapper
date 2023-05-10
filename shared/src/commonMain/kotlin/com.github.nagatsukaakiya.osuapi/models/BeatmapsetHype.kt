package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.Serializable

@Serializable
data class BeatmapsetHype(
    val current: Int,
    val required: Int,
)
