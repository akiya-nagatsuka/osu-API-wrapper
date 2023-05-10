package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WikiPage(
    /** All available locales for the article. */
    @SerialName("available_locales")
    val availableLocales: List<String>,
    /** The layout type for the page. */
    val layout: String,
    /** All lowercase BCP 47 language tag. */
    val locale: String,
    /** Markdown content. */
    val markdown: String,
    /** Path of the article. */
    val path: String,
    /** The article's subtitle. */
    val subtitle: String?,
    /** Associated tags for the article. */
    val tags: List<String>,
    /** The article's title. */
    val title: String,
)
