package com.github.nagatsukaakiya.osuapi.auth

import com.github.nagatsukaakiya.osuapi.platformModule
import com.github.nagatsukaakiya.osuapi.ranking.RankingApi
import org.koin.core.context.startKoin

private val internalApp = startKoin {
    modules(platformModule)
}

val tokenProvider: TokenProvider
    get() = internalApp.koin.get()

val rankingApi: RankingApi
    get() = internalApp.koin.get()
