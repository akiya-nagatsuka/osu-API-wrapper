package com.github.nagatsukaakiya.osuapi

import com.github.nagatsukaakiya.osuapi.auth.TokenLocalProvider
import com.github.nagatsukaakiya.osuapi.auth.TokenProvider
import com.github.nagatsukaakiya.osuapi.auth.TokenProviderImpl
import com.github.nagatsukaakiya.osuapi.beatmaps.BeatmapsApi
import com.github.nagatsukaakiya.osuapi.beatmaps.BeatmapsImpl
import com.github.nagatsukaakiya.osuapi.foundation.createHttpClient
import com.github.nagatsukaakiya.osuapi.news.NewsApi
import com.github.nagatsukaakiya.osuapi.news.NewsApiImpl
import com.github.nagatsukaakiya.osuapi.ranking.RankingApi
import com.github.nagatsukaakiya.osuapi.ranking.RankingApiImpl
import com.github.nagatsukaakiya.osuapi.repository.ChatRepository
import com.github.nagatsukaakiya.osuapi.repository.ChatRepositoryImpl
import com.github.nagatsukaakiya.osuapi.repository.remote.ChatApi
import com.github.nagatsukaakiya.osuapi.repository.remote.ChatRemoteDataProvider
import com.github.nagatsukaakiya.osuapi.repository.remote.ChatRemoteDataProviderImpl
import com.github.nagatsukaakiya.osuapi.repository.remote.FakeChatImpl
import org.koin.dsl.module

fun appModule() = listOf(platformModule)

val platformModule = module {
    single { createHttpClient() }
    single { TokenLocalProvider() }
    single<TokenProvider> { TokenProviderImpl(get()) }
    single<BeatmapsApi> { BeatmapsImpl(get()) }
    single<RankingApi> { RankingApiImpl(get()) }
    single<NewsApi> { NewsApiImpl(get()) }
    single<ChatApi> { FakeChatImpl() }
    single<ChatRemoteDataProvider> { ChatRemoteDataProviderImpl(get(), get()) }
    single<ChatRepository> { ChatRepositoryImpl(get()) }
}
