package com.github.nagatsukaakiya.osuapi.home

import com.github.nagatsukaakiya.osuapi.auth.Token
import com.github.nagatsukaakiya.osuapi.models.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

interface HomeApi {
    suspend fun search(token: Token, mode: Int? = null, query: String? = null, page: Int? = null): SearchResponse
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
