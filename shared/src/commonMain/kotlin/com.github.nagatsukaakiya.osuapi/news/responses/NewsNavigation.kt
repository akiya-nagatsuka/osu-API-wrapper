package com.github.nagatsukaakiya.osuapi.news.responses

import kotlinx.serialization.Serializable

@Serializable
data class NewsNavigation(
    val newer: NewsPost? = null,
    val older: NewsPost? = null,
)
