package com.github.nagatsukaakiya.osuapi.beatmaps.requests

import kotlinx.serialization.Serializable

@Serializable
data class Lookup(
    val checksum: String? = null,
    val filename: String? = null,
    val id: String? = null
)
