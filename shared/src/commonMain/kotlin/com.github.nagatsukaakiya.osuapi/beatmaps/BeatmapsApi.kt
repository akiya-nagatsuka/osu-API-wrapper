package com.github.nagatsukaakiya.osuapi.beatmaps

import com.github.nagatsukaakiya.osuapi.auth.Token
import com.github.nagatsukaakiya.osuapi.beatmaps.requests.Lookup
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders

interface BeatmapsApi {
    /** Returns beatmap. */
    suspend fun lookup(token: Token, checksum: String? = null, filename: String? = null, id: String? = null): String

    /** Return a User's score on a Beatmap. */
    suspend fun getUserScore(token: Token, beatmap: Int, user: Int, mode: String?, mods: String?)

    /** Return a User's scores on a Beatmap. */
    suspend fun getUserScores(token: Token, beatmap: Int, user: Int, mode: String?)

    /** Returns the top scores for a beatmap. */
    suspend fun getScores(token: Token, beatmap: Int, mode: String?, mods: String?, type: String?)

    /** Returns a list of beatmaps. */
    suspend fun getBeatmaps(token: Token, ids: List<Int>?)

    /** Gets beatmap data for the specified beatmap ID. */
    suspend fun getBeatmap(token: Token, beatmap: Int): String

    /** Returns difficulty attributes of beatmap with specific mode and mods combination. */
    suspend fun getBeatmapAttributes(token: Token, beatmap: Int, mods: List<String>?, rulesetId: Int?)
}

internal class BeatmapsImpl(private val client: HttpClient) : BeatmapsApi {

    companion object {
        private const val beatmaps = "https://osu.ppy.sh/api/v2/beatmaps"
    }

    override suspend fun lookup(token: Token, checksum: String?, filename: String?, id: String?): String {
        return client.get("$beatmaps/lookup") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            setBody(Lookup(checksum, filename, id))
        }.body<String>()
    }

    override suspend fun getUserScore(token: Token, beatmap: Int, user: Int, mode: String?, mods: String?) {
        client.get("$beatmaps/$beatmap/scores/users/$user") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()

        // BeatmapUserScore
    }

    override suspend fun getUserScores(token: Token, beatmap: Int, user: Int, mode: String?) {
        client.get("$beatmaps/$beatmap/scores/users/$user/all") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()
    }

    override suspend fun getScores(token: Token, beatmap: Int, mode: String?, mods: String?, type: String?) {
        client.get("$beatmaps/$beatmap/scores") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()
    }

    override suspend fun getBeatmaps(token: Token, ids: List<Int>?) {
        client.get(beatmaps) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()
    }

    override suspend fun getBeatmap(token: Token, beatmap: Int): String {
        return client.get("$beatmaps/$beatmap") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()
    }

    override suspend fun getBeatmapAttributes(token: Token, beatmap: Int, mods: List<String>?, rulesetId: Int?) {
        client.get("$beatmaps/$beatmap/attributes") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()
    }
}
