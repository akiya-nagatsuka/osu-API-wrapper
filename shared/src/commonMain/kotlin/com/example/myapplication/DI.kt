package com.example.myapplication

import com.example.myapplication.auth.TokenProvider
import com.example.myapplication.auth.TokenProviderImpl
import com.example.myapplication.chat.repository.ChatRepository
import com.example.myapplication.chat.repository.ChatRepositoryImpl
import com.example.myapplication.chat.repository.remote.ChatApi
import com.example.myapplication.chat.repository.remote.ChatRemoteDataProvider
import com.example.myapplication.chat.repository.remote.ChatRemoteDataProviderImpl
import com.example.myapplication.chat.repository.remote.FakeChatImpl
import org.koin.dsl.module

fun appModule() = listOf(platformModule)

val platformModule = module {
    single<TokenProvider> { TokenProviderImpl() }
    single<ChatApi> { FakeChatImpl() }
    single<ChatRemoteDataProvider> { ChatRemoteDataProviderImpl(get(), get()) }
    single<ChatRepository> { ChatRepositoryImpl(get()) }
}
