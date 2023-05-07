package com.github.nagatsukaakiya.osuapi.beatmaps

import com.github.nagatsukaakiya.osuapi.auth.Token
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface BeatmapsApi {
    /** Returns beatmap. */
    suspend fun Token.lookup(checksum: String?, filename: String?, id: String?)

    /** Return a User's score on a Beatmap. */
    suspend fun Token.getUserScore(beatmap: Int, user: Int, mode: String?, mods: String?)

    /** Return a User's scores on a Beatmap. */
    suspend fun Token.getUserScores(beatmap: Int, user: Int, mode: String?)

    /** Returns the top scores for a beatmap. */
    suspend fun Token.getScores(beatmap: Int, mode: String?, mods: String?, type: String?)

    /** Returns a list of beatmaps. */
    suspend fun Token.getBeatmaps(ids: List<Int>?)

    /** Gets beatmap data for the specified beatmap ID. */
    suspend fun Token.getBeatmap(beatmap: Int)

    /** Returns difficulty attributes of beatmap with specific mode and mods combination. */
    suspend fun Token.getBeatmapAttributes(beatmap: Int, mods: List<String>?, rulesetId: Int?)
}

internal class BeatmapsImpl : BeatmapsApi {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json(DefaultJson) { ignoreUnknownKeys = true })
            expectSuccess = true
        }
    }

    companion object {
        private const val beatmaps = "https://osu.ppy.sh/api/v2/beatmaps"
    }

    override suspend fun Token.lookup(checksum: String?, filename: String?, id: String?) {
        client.get("${com.github.nagatsukaakiya.osuapi.beatmaps.BeatmapsImpl.beatmaps}/lookup") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(this@lookup.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()
    }

    override suspend fun Token.getUserScore(beatmap: Int, user: Int, mode: String?, mods: String?) {
        client.get("${com.github.nagatsukaakiya.osuapi.beatmaps.BeatmapsImpl.beatmaps}/$beatmap/scores/users/$user") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(this@getUserScore.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()

        // BeatmapUserScore
    }

    override suspend fun Token.getUserScores(beatmap: Int, user: Int, mode: String?) {
        client.get("${com.github.nagatsukaakiya.osuapi.beatmaps.BeatmapsImpl.beatmaps}/$beatmap/scores/users/$user/all") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(this@getUserScores.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()
    }

    override suspend fun Token.getScores(beatmap: Int, mode: String?, mods: String?, type: String?) {
        client.get("${com.github.nagatsukaakiya.osuapi.beatmaps.BeatmapsImpl.beatmaps}/$beatmap/scores") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(this@getScores.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()
    }

    override suspend fun Token.getBeatmaps(ids: List<Int>?) {
        client.get(com.github.nagatsukaakiya.osuapi.beatmaps.BeatmapsImpl.beatmaps) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(this@getBeatmaps.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()
    }

    override suspend fun Token.getBeatmap(beatmap: Int) {
        client.get("${com.github.nagatsukaakiya.osuapi.beatmaps.BeatmapsImpl.beatmaps}/$beatmap") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(this@getBeatmap.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()
    }

    override suspend fun Token.getBeatmapAttributes(beatmap: Int, mods: List<String>?, rulesetId: Int?) {
        client.get("${com.github.nagatsukaakiya.osuapi.beatmaps.BeatmapsImpl.beatmaps}/$beatmap/attributes") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(this@getBeatmapAttributes.value)
            }
            setBody("")
        }.also {
            println(it.status)
            println(it.body() as String)
        }.body<String>()
    }
}
