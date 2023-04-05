package com.example.myapplication

import com.example.myapplication.auth.TokenProviderImpl
import io.github.aakira.napier.Napier

class Greeting {
    private val tokenProvider = TokenProviderImpl()

    suspend fun greeting(): String {
        tokenProvider.authorise()
        Napier.d("Success")
        return "Success"
    }
}
