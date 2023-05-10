package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsSidebar(
    @SerialName("current_year")
    val currentYear: Int,
    @SerialName("news_posts")
    val newsPosts: List<NewsPost>,
    val years: List<Int>,
)
