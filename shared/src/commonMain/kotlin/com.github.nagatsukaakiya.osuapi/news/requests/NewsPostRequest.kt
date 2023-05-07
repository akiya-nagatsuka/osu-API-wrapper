package com.github.nagatsukaakiya.osuapi.news.requests

import kotlinx.serialization.Serializable

@Serializable
data class NewsPostRequest(val news: String, val key: String? = null)
