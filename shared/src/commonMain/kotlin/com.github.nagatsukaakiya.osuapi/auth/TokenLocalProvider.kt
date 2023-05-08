package com.github.nagatsukaakiya.osuapi.auth

import io.github.reactivecircus.cache4k.Cache

internal class TokenLocalProvider(
    private val cache: Cache<String, String> = Cache.Builder<String, String>().build()
) {
    companion object {
        private const val CODE_KEY = "code"
        private const val REFRESH_KEY = "refresh"
    }

    fun putCode(code: String) = cache.put(CODE_KEY, code)

    fun getCode() = cache.get(CODE_KEY)

    fun putRefreshToken(refreshToken: String) = cache.put(REFRESH_KEY, refreshToken)

    fun getRefreshToken() = cache.get(REFRESH_KEY)
}
