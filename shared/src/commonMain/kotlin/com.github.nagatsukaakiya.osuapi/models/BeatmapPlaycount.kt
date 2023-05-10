package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.Serializable

@Serializable
data class BeatmapPlaycount(
    val beatmap_id: Int,
    val beatmap: BeatmapCompact?,
    val beatmapset: BeatmapsetCompact?,
    val count: Int,
)
