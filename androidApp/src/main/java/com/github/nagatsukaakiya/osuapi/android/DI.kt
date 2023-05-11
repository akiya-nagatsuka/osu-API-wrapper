package com.github.nagatsukaakiya.osuapi.android

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single { UserRepository(get(), get()) }
    viewModel { CompareViewModel(get()) }
}
