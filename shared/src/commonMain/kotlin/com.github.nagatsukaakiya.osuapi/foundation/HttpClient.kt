package com.github.nagatsukaakiya.osuapi.foundation

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun createHttpClient() = HttpClient {
    install(ContentNegotiation) {
        json(Json(DefaultJson) { ignoreUnknownKeys = true })
        expectSuccess = true
    }
}
