package com.github.nagatsukaakiya.osuapi.ranking.requests

import kotlinx.serialization.Serializable

@Serializable
data class RankingsRequest(
    /** Filter ranking by country code. Only available for type of performance. */
    val country: Int?,
    /** Cursor. */
    val cursor: String?,
    /** Either all (default) or friends. */
    val filter: String?,
    /**
     * The id of the spotlight if type is charts.
     * Ranking for latest spotlight will be returned if not specified.
     */
    val spotlight: String?,
    /**
     * Filter ranking to specified mode variant.
     * For mode of mania, it's either 4k or 7k.
     * Only available for type of performance.
     */
    val variant: String?,
)
