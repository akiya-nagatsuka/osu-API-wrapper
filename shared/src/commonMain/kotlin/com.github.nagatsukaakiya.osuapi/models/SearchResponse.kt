package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    /** For all or user mode. Only first 100 results are accessible */
    val user: SearchResult<UserCompact>? = null,
    /** For all or wiki_page mode */
    @SerialName("wiki_page")
    val wikiPage: SearchResult<WikiPage>? = null,
)
