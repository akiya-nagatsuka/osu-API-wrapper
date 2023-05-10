package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeatmapsetCompact(
    val artist: String,
    @SerialName("artist_unicode")
    val artistUnicode: String,
    val covers: Covers,
    val creator: String,
    @SerialName("favourite_count")
    val favouriteCount: Int,
    val id: Int,
    val nsfw: Boolean,
    @SerialName("play_count")
    val playCount: Int,
    @SerialName("preview_url")
    val previewUrl: String,
    val source: String,
    val status: String,
    val title: String,
    @SerialName("title_unicode")
    val titleUnicode: String,
    @SerialName("user_id")
    val userId: Int,
    val video: Boolean,
)
