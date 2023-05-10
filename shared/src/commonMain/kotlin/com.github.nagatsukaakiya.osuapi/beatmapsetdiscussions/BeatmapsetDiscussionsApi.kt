package com.github.nagatsukaakiya.osuapi.beatmapsetdiscussions

import com.github.nagatsukaakiya.osuapi.auth.Token
import com.github.nagatsukaakiya.osuapi.home.HomeApi
import com.github.nagatsukaakiya.osuapi.models.BeatmapsetDiscussionPostsVotesResponse
import com.github.nagatsukaakiya.osuapi.models.BeatmapsetDiscussionResponse
import com.github.nagatsukaakiya.osuapi.models.BeatmapsetDiscussionsPostsResponse
import com.github.nagatsukaakiya.osuapi.models.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

interface BeatmapsetDiscussionsApi {
    suspend fun getPosts(token: Token): BeatmapsetDiscussionsPostsResponse
    suspend fun getVotes(token: Token): BeatmapsetDiscussionPostsVotesResponse
    suspend fun getDiscussions(token: Token): BeatmapsetDiscussionResponse
}

internal class BeatmapsetDiscussionsApiImpl(private val client: HttpClient): BeatmapsetDiscussionsApi {
    companion object {
        private const val discussions = "https://osu.ppy.sh/api/v2/beatmapsets/discussions"
    }

    override suspend fun getPosts(token: Token): BeatmapsetDiscussionsPostsResponse {
        return client.get("$discussions/posts") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            // TODO Set Body
//            setBody(NewsListRequest(limit, year, cursorString))
        }.body()
    }

    override suspend fun getVotes(token: Token): BeatmapsetDiscussionPostsVotesResponse {
        return client.get("$discussions/votes") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            // TODO Set Body
//            setBody(NewsListRequest(limit, year, cursorString))
        }.body()
    }

    override suspend fun getDiscussions(token: Token): BeatmapsetDiscussionResponse {
        return client.get(discussions) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            // TODO Set Body
//            setBody(NewsListRequest(limit, year, cursorString))
        }.body()
    }
}

internal class HomeApiImpl(private val client: HttpClient): HomeApi {
    companion object {
        private const val search = "https://osu.ppy.sh/api/v2/search"
    }

    override suspend fun search(
        token: Token,
        mode: Int?,
        query: String?,
        page: Int?
    ): SearchResponse {
        return client.get(search) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                bearerAuth(token.value)
            }
            // TODO Set Body
//            setBody(NewsListRequest(limit, year, cursorString))
        }.body()
    }
}

