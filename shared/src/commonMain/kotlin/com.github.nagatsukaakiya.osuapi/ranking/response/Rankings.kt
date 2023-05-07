package com.github.nagatsukaakiya.osuapi.ranking.response

import kotlinx.serialization.Serializable

@Serializable
data class Rankings(
    /**
     * The list of beatmaps in the requested spotlight for the given mode;
     * only available if type is charts
     */
    val beatmapsets: List<Beatmapset>?,
//    val cursor: JsonElement,
    /** Score details ordered by rank in descending order. */
//    val ranking: String,
    /** Spotlight details; only available if type is charts */
    val spotlight: String? = null,
    /** An approximate count of ranks available */
    val total: Int,
)
