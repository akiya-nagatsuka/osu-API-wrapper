package com.github.nagatsukaakiya.osuapi.models

import com.github.nagatsukaakiya.osuapi.models.Score

data class BeatmapUserScore(
    val position: Int,
    val score: Score,
)
