@file:Suppress("UNSUPPORTED_FEATURE")

package com.github.nagatsukaakiya.osuapi.news

import com.github.nagatsukaakiya.osuapi.news.requests.NewsListRequest
import com.github.nagatsukaakiya.osuapi.news.responses.NewsListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders

interface NewsApi {
    suspend fun newsList(
        limit: Int? = null,
        year: Int? = null,
        cursorString: String? = null
    ): NewsListResponse

    suspend fun newsPost()
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

    override suspend fun newsPost() {
        TODO("Not yet implemented")
    }
}
