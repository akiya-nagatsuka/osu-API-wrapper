package com.github.nagatsukaakiya.osuapi.wiki

import com.github.nagatsukaakiya.osuapi.models.WikiPage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

interface WikiApi {
    suspend fun getWikiPage(locale: String, path: String): WikiPage
}

class WikiApiImpl(private val client: HttpClient): WikiApi {
    companion object {
        private const val wiki = "https://osu.ppy.sh/api/v2/wiki"
    }
    override suspend fun getWikiPage(locale: String, path: String): WikiPage {
        return client.get("$wiki/$locale/$path") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
            }
        }.body()
    }
}
