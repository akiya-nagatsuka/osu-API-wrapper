package com.example.myapplication

import com.example.myapplication.auth.TokenProvider
import com.example.myapplication.auth.TokenProviderImpl
import org.koin.dsl.module

fun appModule() = listOf(platformModule)

val platformModule = module {
    single<TokenProvider> { TokenProviderImpl() }
}
