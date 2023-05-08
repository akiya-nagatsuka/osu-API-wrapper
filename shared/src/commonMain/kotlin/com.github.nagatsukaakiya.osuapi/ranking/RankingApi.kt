package com.github.nagatsukaakiya.osuapi.ranking

import com.github.nagatsukaakiya.osuapi.auth.Token
import com.github.nagatsukaakiya.osuapi.ranking.requests.GameMode
import com.github.nagatsukaakiya.osuapi.ranking.requests.RankingType
import com.github.nagatsukaakiya.osuapi.ranking.requests.RankingsRequest
import com.github.nagatsukaakiya.osuapi.ranking.response.Rankings
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders

interface RankingApi {
    suspend fun getRanking(token: Token, mode: GameMode, type: RankingType): Rankings
}

internal class RankingApiImpl(private val client: HttpClient) : RankingApi {
    companion object {
        private const val rankings = "https://osu.ppy.sh/api/v2/rankings"
    }

    override suspend fun getRanking(token: Token, mode: GameMode, type: RankingType): Rankings {
        return client.get("$rankings/${mode.getValue()}/${type.getValue()}") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            setBody(RankingsRequest(mode, type))
        }.body()
    }
}
