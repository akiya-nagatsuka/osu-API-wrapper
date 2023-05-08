package com.github.nagatsukaakiya.osuapi.ranking

import com.github.nagatsukaakiya.osuapi.auth.Token
import com.github.nagatsukaakiya.osuapi.ranking.requests.GameMode
import com.github.nagatsukaakiya.osuapi.ranking.requests.RankingType
import com.github.nagatsukaakiya.osuapi.ranking.requests.RankingsRequest
import com.github.nagatsukaakiya.osuapi.ranking.response.Rankings
import com.github.nagatsukaakiya.osuapi.ranking.response.Spotlight
import com.github.nagatsukaakiya.osuapi.ranking.response.Spotlights
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders

interface RankingApi {
    suspend fun getRanking(
        token: Token,
        mode: GameMode,
        type: RankingType,
        country: Int? = null,
        cursor: String? = null,
        filter: String? = null,
        spotlight: String? = null,
        variant: String? = null,
    ): Rankings

    suspend fun getSpotlights(token: Token): List<Spotlight>
}

internal class RankingApiImpl(private val client: HttpClient) : RankingApi {
    companion object {
        private const val rankings = "https://osu.ppy.sh/api/v2/rankings"
        private const val spotlights = "https://osu.ppy.sh/api/v2/spotlights"
    }

    override suspend fun getRanking(
        token: Token,
        mode: GameMode,
        type: RankingType,
        country: Int?,
        cursor: String?,
        filter: String?,
        spotlight: String?,
        variant: String?
    ): Rankings {
        return client.get("$rankings/${mode.getValue()}/${type.getValue()}") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            setBody(RankingsRequest(country, cursor, filter, spotlight, variant))
        }.body()
    }

    override suspend fun getSpotlights(token: Token): List<Spotlight> {
        return client.get(spotlights) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
        }.body<Spotlights>().spotlights
    }
}
