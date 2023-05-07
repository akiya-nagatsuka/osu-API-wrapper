package com.github.nagatsukaakiya.osuapi.news.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NewsListRequest(
    val limit: Int? = null,
    val year: Int? = null,
    @SerialName("cursor_string")
    val cursorString: String? = null,
)
