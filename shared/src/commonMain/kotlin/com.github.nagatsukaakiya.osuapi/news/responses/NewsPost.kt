package com.github.nagatsukaakiya.osuapi.news.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsPost(
    private val author: String,
    /** Link to the file view on GitHub. */
    @SerialName("edit_url")
    private val editUrl: String,
    /** Link to the first image in the document. */
    @SerialName("first_image")
    private val firstImage: String?,
    private val id: Int,
    @SerialName("published_at")
    private val publishedAt: String,
    /** Filename without the extension, used in URLs. */
    private val slug: String,
    private val title: String,
    @SerialName("updated_at")
    private val updatedAt: String,
)
