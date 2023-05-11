package com.github.nagatsukaakiya.osuapi.users.requests

import com.github.nagatsukaakiya.osuapi.ranking.requests.GameMode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserScoreRequest(
//    @SerialName("include_fails")
//    val includeFails: Int?,
//    val mode: GameMode?,
//    val limit: Int?,
    val offset: Int?
)
