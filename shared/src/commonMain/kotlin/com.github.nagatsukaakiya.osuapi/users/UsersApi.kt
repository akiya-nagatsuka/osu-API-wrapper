package com.github.nagatsukaakiya.osuapi.users

import com.github.nagatsukaakiya.osuapi.auth.Token
import com.github.nagatsukaakiya.osuapi.models.BeatmapPlaycount
import com.github.nagatsukaakiya.osuapi.models.Beatmapset
import com.github.nagatsukaakiya.osuapi.models.Event
import com.github.nagatsukaakiya.osuapi.ranking.requests.GameMode
import com.github.nagatsukaakiya.osuapi.users.requests.KudosuRequest
import com.github.nagatsukaakiya.osuapi.users.requests.UserScoreRequest
import com.github.nagatsukaakiya.osuapi.models.KudosuHistory
import com.github.nagatsukaakiya.osuapi.models.Score
import com.github.nagatsukaakiya.osuapi.models.User
import com.github.nagatsukaakiya.osuapi.models.UserCompact
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders

interface UsersApi {
    suspend fun getOwnData(token: Token, mode: GameMode? = null): User

    suspend fun getKudosu(token: Token, user: Int, limit: Int? = null, offset: String? = null): KudosuHistory

    suspend fun getUserScores(
        token: Token,
        user: Int,
        /** Score type. Must be one of these: best, firsts, recent. */
        type: String,
        includeFails: Int? = null,
        mode: GameMode? = null,
        limit: Int? = null,
        offset: String? = null,
    ): List<Score>

    suspend fun getUserBeatmaps(
        token: Token,
        user: Int,
        type: String,
        limit: Int? = null,
        offset: String? = null,
    ): UserBeatmapsResponse

    suspend fun getUserRecentActivity(
        token: Token,
        user: Int,
        limit: Int? = null,
        offset: String? = null,
    ): List<Event>

    suspend fun getUser(token: Token, user: Int, mode: GameMode? = null, key: String? = null): User

    suspend fun getUsers(token: Token, ids: List<Int>? = null): List<UserCompact>
}

internal class UsersApiImpl(private val client: HttpClient): UsersApi {
    companion object {
        private const val me = "https://osu.ppy.sh/api/v2/me"
        private const val users = "https://osu.ppy.sh/api/v2/users"
    }

    override suspend fun getOwnData(token: Token, mode: GameMode?): User {
        return client.get(if (mode == null) me else "$me/${mode.getValue()}") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
        }.body()
    }

    override suspend fun getKudosu(token: Token, user: Int, limit: Int?, offset: String?): KudosuHistory {
        return client.get("$users/$user/kudosu") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            setBody(KudosuRequest(limit, offset))
        }.body()
    }

    override suspend fun getUserScores(
        token: Token,
        user: Int,
        type: String,
        includeFails: Int?,
        mode: GameMode?,
        limit: Int?,
        offset: String?
    ): List<Score> {
        return client.get("$users/$user/scores/$type") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            setBody(UserScoreRequest(includeFails, mode, limit, offset))
        }.body()
    }

    override suspend fun getUserBeatmaps(
        token: Token,
        user: Int,
        type: String,
        limit: Int?,
        offset: String?
    ): UserBeatmapsResponse {
        return client.get("$users/$user/beatmapsets/$type") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            // TODO Add Body
//            setBody(UserScoreRequest(limit, offset))
        }.run {
            if (type == "most_played") {
                UserBeatmapsResponse(body<List<BeatmapPlaycount>>())
            } else {
                UserBeatmapsResponse(beatmaps = body<List<Beatmapset>>())
            }
        }
    }

    override suspend fun getUserRecentActivity(
        token: Token,
        user: Int,
        limit: Int?,
        offset: String?
    ): List<Event> {
        return client.get("$users/$user/recent_activity") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            // TODO Add Body
//            setBody(UserScoreRequest(limit, offset))
        }.body<List<Event>>()
    }

    override suspend fun getUser(token: Token, user: Int, mode: GameMode?, key: String?): User {
        return client.get(if (mode == null) "$users/$user" else "$users/$user/${mode.getValue()}") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            // TODO Set Body
        }.body()
    }

    override suspend fun getUsers(token: Token, ids: List<Int>?): List<UserCompact> {
        return client.get(users) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            // TODO Set Body
        }.body()
    }
}
