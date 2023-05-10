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
import com.github.nagatsukaakiya.osuapi.chats.ChatRepository
import com.github.nagatsukaakiya.osuapi.chats.ChatRepositoryImpl
import com.github.nagatsukaakiya.osuapi.chats.remote.ChatApi
import com.github.nagatsukaakiya.osuapi.chats.remote.ChatRemoteDataProvider
import com.github.nagatsukaakiya.osuapi.chats.remote.ChatRemoteDataProviderImpl
import com.github.nagatsukaakiya.osuapi.chats.remote.FakeChatImpl
import com.github.nagatsukaakiya.osuapi.users.UsersApi
import com.github.nagatsukaakiya.osuapi.users.UsersApiImpl
import org.koin.dsl.module

fun appModule() = listOf(platformModule)

val platformModule = module {
    single { createHttpClient() }
    single { TokenLocalProvider() }
    single<TokenProvider> { TokenProviderImpl(get()) }
    single<BeatmapsApi> { BeatmapsImpl(get()) }
    single<RankingApi> { RankingApiImpl(get()) }
    single<UsersApi> { UsersApiImpl(get()) }
    single<NewsApi> { NewsApiImpl(get()) }
    single<ChatApi> { FakeChatImpl() }
    single<ChatRemoteDataProvider> { ChatRemoteDataProviderImpl(get(), get()) }
    single<ChatRepository> { ChatRepositoryImpl(get()) }
}
