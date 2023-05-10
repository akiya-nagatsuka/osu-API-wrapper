package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.Serializable

@Serializable
data class Kudosu(
    val available: Int,
    val total: Int,
)
