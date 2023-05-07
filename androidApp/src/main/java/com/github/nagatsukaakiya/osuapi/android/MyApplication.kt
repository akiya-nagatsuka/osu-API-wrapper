package com.github.nagatsukaakiya.osuapi.android

import android.app.Application
import com.github.nagatsukaakiya.osuapi.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            androidLogger()
            modules(appModule() + androidModule)
        }
    }
}
