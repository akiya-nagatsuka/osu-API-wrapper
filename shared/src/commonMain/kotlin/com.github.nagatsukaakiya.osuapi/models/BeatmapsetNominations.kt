package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.Serializable

@Serializable
data class BeatmapsetNominations(
    val current: Int,
    val required: Int,
)
