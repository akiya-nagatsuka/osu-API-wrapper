package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserCompact(
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("default_group")
    val defaultGroup: String,
    val id: String,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("is_bot")
    val isBot: Boolean,
    @SerialName("is_deleted")
    val isDeleted: Boolean,
    @SerialName("is_online")
    val isOnline: Boolean,
    @SerialName("is_supporter")
    val isSupporter: Boolean,
    @SerialName("last_visit")
    val lastVisit: String? = null,
    @SerialName("pm_friends_only")
    val pmFriendsOnly: Boolean,
    @SerialName("profile_colour")
    val profileColour: String? = null,
    val username: String,
)
