package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.Serializable

@Serializable
data class SearchResult<T: Any>(
    val data: List<T>,
    val total: Int,
)
