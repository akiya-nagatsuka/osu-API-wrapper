package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangelogResponse(
    /** Includes changelog_entries, changelog_entries.github_user, and changelog entry message in requested formats. */
    val builds: List<Build>,
    val search: Search,
    /** Always contains all available streams. Includes latest_build and user_count. */
    val streams: List<UpdateStream>,
)

@Serializable
data class Search(
    /** from input. */
    val from: String?,
    /** Always 21. */
    val limit: Int,
    /** max_id input. */
    @SerialName("max_id")
    val maxId: Int?,
    /** stream input. */
    val stream: String?,
    /** to input. */
    val to: String?,
)
