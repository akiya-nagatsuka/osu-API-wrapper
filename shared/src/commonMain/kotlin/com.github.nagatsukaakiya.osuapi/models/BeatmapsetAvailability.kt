package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeatmapsetAvailability(
    @SerialName("download_disabled")
    val downloadDisabled: Boolean,
    @SerialName("more_information")
    val moreInformation: String?,
)
