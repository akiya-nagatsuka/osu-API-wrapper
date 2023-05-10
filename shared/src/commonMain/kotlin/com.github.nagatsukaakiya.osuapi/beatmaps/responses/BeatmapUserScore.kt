package com.github.nagatsukaakiya.osuapi.beatmaps.responses

import com.github.nagatsukaakiya.osuapi.users.responses.Score

data class BeatmapUserScore(
    val position: Int,
    val score: Score,
)
