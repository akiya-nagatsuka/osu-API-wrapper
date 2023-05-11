package com.github.nagatsukaakiya.osuapi.news

import com.github.nagatsukaakiya.osuapi.models.NewsListResponse
import com.github.nagatsukaakiya.osuapi.models.NewsPost
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface NewsApi {
    suspend fun newsList(
        limit: Int? = null,
        year: Int? = null,
        cursorString: String? = null
    ): NewsListResponse

    suspend fun newsPost(news: String, key: String? = null): NewsPost
}

internal class NewsApiImpl(private val client: HttpClient) : NewsApi {
    companion object {
        private const val news = "https://osu.ppy.sh/api/v2/news"
    }

    override suspend fun newsList(limit: Int?, year: Int?, cursorString: String?): NewsListResponse {
        return client.get(news) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
            }
            setBody(NewsListRequest(limit, year, cursorString))
        }.body()
    }

    override suspend fun newsPost(news: String, key: String?): NewsPost {
        return client.get("${Companion.news}/$news") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
            }
            setBody(NewsPostRequest(key))
        }.body()
    }

    @Serializable
    private data class NewsListRequest(
        val limit: Int? = null,
        val year: Int? = null,
        @SerialName("cursor_string")
        val cursorString: String? = null,
    )

    @Serializable
    private data class NewsPostRequest(val key: String? = null)
}
