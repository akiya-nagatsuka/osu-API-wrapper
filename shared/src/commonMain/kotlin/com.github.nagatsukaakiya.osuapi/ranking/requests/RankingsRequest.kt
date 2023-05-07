package com.github.nagatsukaakiya.osuapi.ranking.requests

import kotlinx.serialization.Serializable

@Serializable
data class RankingsRequest(val mode: GameMode, val type: RankingType)
