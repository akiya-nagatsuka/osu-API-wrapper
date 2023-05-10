package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KudosuHistory(
    val id: Int,
    /** One of give, vote.give, reset, vote.reset, revoke, or vote.revoke. */
    val action: String,
    val amount: Int,
    /** Object type which the exchange happened on (forum_post, etc). */
    val model: String,
    @SerialName("created_at")
    val createdAt: String,
    /** Simple detail of the user who started the exchange. */
    val giver: Giver?,
    /** Simple detail of the object for display. */
    val post: Post,
)

@Serializable
data class Giver(
    val url: String,
    val username: String,
)

@Serializable
data class Post(
    /** Url of the object. */
    val url: String?,
    /** Title of the object. It'll be "[deleted beatmap]" for deleted beatmaps. */
    val title: String,
)
