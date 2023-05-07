package com.github.nagatsukaakiya.osuapi.news.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsSidebar(
    @SerialName("current_year")
    val currentYear: Int,
    @SerialName("news_posts")
    val newsPosts: Int,
    val years: List<Int>,
)
