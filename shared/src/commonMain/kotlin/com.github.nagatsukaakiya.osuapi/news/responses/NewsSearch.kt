package com.github.nagatsukaakiya.osuapi.news.responses

import kotlinx.serialization.Serializable

@Serializable
data class NewsSearch(
    val limit: Int,
    val sort: String,
)
