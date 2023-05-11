package com.github.nagatsukaakiya.osuapi.beatmapsetdiscussions

import com.github.nagatsukaakiya.osuapi.auth.Token
import com.github.nagatsukaakiya.osuapi.models.BeatmapsetDiscussionPostsVotesResponse
import com.github.nagatsukaakiya.osuapi.models.BeatmapsetDiscussionResponse
import com.github.nagatsukaakiya.osuapi.models.BeatmapsetDiscussionsPostsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders

interface BeatmapsetDiscussionsApi {
    suspend fun getPosts(
        token: Token,
        beatmapsetDiscussionId: String? = null,
        limit: Int? = null,
        page: Int? = null,
        sort: String? = null,
        types: List<String>? = null,
        user: String? = null,
        withDeleted: String? = null,
    ): BeatmapsetDiscussionsPostsResponse
    suspend fun getVotes(
        token: Token,
        beatmapsetDiscussionId: String? = null,
        limit: Int? = null,
        page: Int? = null,
        receiver: String? = null,
        score: String? = null,
        sort: String? = null,
        user: String? = null,
        withDeleted: String? = null,
    ): BeatmapsetDiscussionPostsVotesResponse
    suspend fun getDiscussions(
        token: Token,
        beatmapId: String? = null,
        beatmapsetId: String? = null,
        beatmapsetStatus: String? = null,
        limit: Int? = null,
        messageTypes: List<String>? = null,
        onlyUnresolved: String? = null,
        page: Int? = null,
        sort: String? = null,
        user: String? = null,
        withDeleted: String? = null,
    ): BeatmapsetDiscussionResponse
}

internal class BeatmapsetDiscussionsApiImpl(private val client: HttpClient): BeatmapsetDiscussionsApi {
    companion object {
        private const val discussions = "https://osu.ppy.sh/api/v2/beatmapsets/discussions"
    }

    override suspend fun getPosts(
        token: Token,
        beatmapsetDiscussionId: String?,
        limit: Int?,
        page: Int?,
        sort: String?,
        types: List<String>?,
        user: String?,
        withDeleted: String?,
    ): BeatmapsetDiscussionsPostsResponse {
        return client.get("$discussions/posts") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            parameter("beatmapset_discussion_id", beatmapsetDiscussionId)
            parameter("limit", limit)
            parameter("page", page)
            parameter("sort", sort)
            parameter("types", types)
            parameter("user", user)
            parameter("with_deleted", withDeleted)
        }.body()
    }

    override suspend fun getVotes(
        token: Token,
        beatmapsetDiscussionId: String?,
        limit: Int?,
        page: Int?,
        receiver: String?,
        score: String?,
        sort: String?,
        user: String?,
        withDeleted: String?,
    ): BeatmapsetDiscussionPostsVotesResponse {
        return client.get("$discussions/votes") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            parameter("beatmapset_discussion_id", beatmapsetDiscussionId)
            parameter("limit", limit)
            parameter("page", page)
            parameter("sort", sort)
            parameter("score", score)
            parameter("receiver", receiver)
            parameter("user", user)
            parameter("with_deleted", withDeleted)
        }.body()
    }

    override suspend fun getDiscussions(
        token: Token,
        beatmapId: String?,
        beatmapsetId: String?,
        beatmapsetStatus: String?,
        limit: Int?,
        messageTypes: List<String>?,
        onlyUnresolved: String?,
        page: Int?,
        sort: String?,
        user: String?,
        withDeleted: String?,
    ): BeatmapsetDiscussionResponse {
        return client.get(discussions) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            parameter("beatmap_id", beatmapId)
            parameter("beatmapset_id", beatmapsetId)
            parameter("beatmapset_status", beatmapsetStatus)
            parameter("limit", limit)
            parameter("page", page)
            parameter("sort", sort)
            parameter("message_types", messageTypes)
            parameter("only_unresolved", onlyUnresolved)
            parameter("user", user)
            parameter("with_deleted", withDeleted)
        }.body()
    }
}

