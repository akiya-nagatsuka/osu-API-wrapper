package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Beatmapset(
    val availability: BeatmapsetAvailability,
    val bpm: Float,
    @SerialName("can_be_hyped")
    val canBeHyped: Boolean,
    /** Username of the mapper at the time of beatmapset creation. */
    val creator: String,
    @SerialName("deleted_at")
    val deletedAt: String?,
    @SerialName("discussion_locked")
    val discussionLocked: Boolean,
    val hype: BeatmapsetHype,
    @SerialName("is_scoreable")
    val isScoreable: Boolean,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("legacy_thread_url")
    val legacyThreadUrl: String?,
    val nominations: BeatmapsetNominations,
    /** See Rank status for list of possible values. */
    val ranked: Int,
    @SerialName("ranked_date")
    val rankedDate: String?,
    val source: String,
    val storyboard: Boolean,
    @SerialName("submitted_date")
    val submittedDate: String?,
    val tags: String,
)
