package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsListResponse(
    @SerialName("news_posts")
    val newsPost: List<NewsPost>,
    @SerialName("cursor_string")
    val cursorString: String,
    @SerialName("news_sidebar")
    val newsSidebar: NewsSidebar,
    val search: NewsSearch,
)
