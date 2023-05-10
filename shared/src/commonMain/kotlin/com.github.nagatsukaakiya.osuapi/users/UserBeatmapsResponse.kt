package com.github.nagatsukaakiya.osuapi.users

import com.github.nagatsukaakiya.osuapi.models.BeatmapPlaycount
import com.github.nagatsukaakiya.osuapi.models.Beatmapset

data class UserBeatmapsResponse(
    val playcounts: List<BeatmapPlaycount>? = null,
    val beatmaps: List<Beatmapset>? = null,
)
