package com.github.nagatsukaakiya.osuapi.ranking.response

import kotlinx.serialization.Serializable

@Serializable
data class GradeCounts(
    /** Number of A ranked scores. */
    val a: Int,
    /** Number of S ranked scores. */
    val s: Int,
    /** Number of Silver S ranked scores. */
    val sh: Int,
    /** Number of SS ranked scores. */
    val ss: Int,
    /** Number of Silver SS ranked scores. */
    val ssh: Int,
)
