package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsPost(
    val author: String,
    /** Link to the file view on GitHub. */
    @SerialName("edit_url")
    val editUrl: String,
    /** Link to the first image in the document. */
    @SerialName("first_image")
    val firstImage: String?,
    val id: Int,
    @SerialName("published_at")
    val publishedAt: String,
    /** Filename without the extension, used in URLs. */
    val slug: String,
    val title: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val content: String? = null,
    val navigation: NewsNavigation? = null,
)
