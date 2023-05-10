package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsSearch(
    @SerialName("limit")
    val limit: Int,
    @SerialName("sort")
    val sort: String,
)
