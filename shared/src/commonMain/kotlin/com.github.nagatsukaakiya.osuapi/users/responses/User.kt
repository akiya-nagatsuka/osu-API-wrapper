package com.github.nagatsukaakiya.osuapi.users.responses

import com.github.nagatsukaakiya.osuapi.ranking.requests.GameMode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class User(
    val discord: String? = null,
    /** whether or not ever being a supporter in the past */
    @SerialName("has_supported")
    val hasSupported: Boolean,
    val interests: String? = null,
    @SerialName("join_date")
    val joinDate: String,
    val kudosu: Kudosu,
    val location: String? = null,
    /** maximum number of users allowed to be blocked */
    @SerialName("max_blocks")
    val maxBlocks: Int,
    /** maximum number of friends allowed to be added */
    @SerialName("max_friends")
    val maxFriends: Int,
    val occupation: String? = null,
    val playmode: GameMode,
    /** Device choices of the user. */
    val playstyle: List<String>,
    /** number of forum posts */
    @SerialName("post_count")
    val postCount: Int,
    /** ordered array of sections in user profile page */
    @SerialName("profile_order")
    val profileOrder: List<String>,
    /** user-specific title */
    val title: String? = null,
    @SerialName("title_url")
    val titleUrl: String? = null,
    val twitter: String? = null,
    val website: String? = null,

    // From UserCompact
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

    // Only for me
    @SerialName("statistics_rulesets")
    val statisticsRulesets: JsonElement? = null,
)

@Serializable
data class Kudosu(
    val available: Int,
    val total: Int,
)
