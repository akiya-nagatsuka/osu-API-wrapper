package com.github.nagatsukaakiya.osuapi.changelog

import com.github.nagatsukaakiya.osuapi.models.Build
import com.github.nagatsukaakiya.osuapi.models.ChangelogResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders

interface ChangelogApi {
    suspend fun getChangelogBuild(stream: String, build: String): Build
    suspend fun getChangelog(
        from: String? = null,
        maxId: Int? = null,
        stream: String? = null,
        to: String? = null,
        messageFormats: List<String>? = null,
    ): ChangelogResponse

    suspend fun lookupChangelogBuild(
        changelog: String,
        key: String?,
        messageFormats: String?,
    ): Build
}

internal class ChangelogApiImpl(private val client: HttpClient): ChangelogApi {
    companion object {
        private const val changelog = "https://osu.ppy.sh/api/v2/changelog"
    }
    override suspend fun getChangelogBuild(stream: String, build: String): Build {
        return client.get("$changelog/$stream/$build") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
            }
        }.body()
    }

    override suspend fun getChangelog(
        from: String?,
        maxId: Int?,
        stream: String?,
        to: String?,
        messageFormats: List<String>?,
    ): ChangelogResponse {
        return client.get(changelog) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
            }
            parameter("from", from)
            parameter("max_id", maxId)
            parameter("stream", stream)
            parameter("to", to)
            parameter("messageFormats", messageFormats)
        }.body()
    }

    override suspend fun lookupChangelogBuild(
        changelog: String,
        key: String?,
        messageFormats: String?,
    ): Build {
        return client.get("${Companion.changelog}/$changelog") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
            }
            parameter("key", key)
            parameter("message_formats", messageFormats)
        }.body()
    }
}
