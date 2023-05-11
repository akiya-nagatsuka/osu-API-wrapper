package com.github.nagatsukaakiya.osuapi.foundation

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

internal fun createHttpClient() = HttpClient {
    install(ContentNegotiation) {
        json(Json(DefaultJson) { ignoreUnknownKeys = true })
        expectSuccess = true
    }
}

internal fun <T> HttpRequestBuilder.parameters(serializer: SerializationStrategy<T>, value: T) {
    url {
        Json.encodeToJsonElement(serializer, value).jsonObject.entries.forEach {
            parameters.append(it.key, it.value.toString())
        }
    }
}
