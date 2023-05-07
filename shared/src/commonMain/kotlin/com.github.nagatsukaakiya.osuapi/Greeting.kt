package com.github.nagatsukaakiya.osuapi

import com.github.nagatsukaakiya.osuapi.auth.TokenProviderImpl
import io.github.aakira.napier.Napier

class Greeting {
    private val tokenProvider = TokenProviderImpl()

    suspend fun greeting(): String {
        tokenProvider.authorise()
        Napier.d("Success")
        return "Success"
    }
}
