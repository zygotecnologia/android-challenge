package com.zygotecnologia.zygotv

import android.app.Application
import com.zygotecnologia.zygotv.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ZygoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ZygoApplication)

            modules(listOf(homeModule))
        }
    }
}