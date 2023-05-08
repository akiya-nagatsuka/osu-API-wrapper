package com.github.nagatsukaakiya.osuapi.ranking.response

import kotlinx.serialization.Serializable

@Serializable
data class Level(
    /** Current level. */
    val current: Int,
    /** Progress to next level. */
    val progress: Int,
)
