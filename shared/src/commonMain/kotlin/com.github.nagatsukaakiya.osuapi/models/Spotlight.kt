package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spotlight(
    /** The end date of the spotlight. */
    @SerialName("end_date")
    val endDate: String,
    /** The ID of this spotlight. */
    val id: Int,
    /** If the spotlight has different mades specific to each GameMode. */
    @SerialName("mode_specific")
    val modeSpecific: Boolean,
    /** The number of users participating in this spotlight. This is only shown when viewing a single spotlight. */
    @SerialName("participant_count")
    val participantCount: Int?,
    /** The name of the spotlight. */
    val name: String,
    /** The starting date of the spotlight. */
    @SerialName("start_date")
    val startDate: String,
    /** The type of spotlight. */
    val type: String,
)
