package com.github.nagatsukaakiya.osuapi.users.requests

import kotlinx.serialization.Serializable

@Serializable
data class KudosuRequest(
    val limit: Int?,
    val offset: String?,
)
