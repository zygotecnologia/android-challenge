package com.zygotecnologia.zygotv

import android.app.Application
import com.zygotecnologia.zygotv.di.homeModule
import com.zygotecnologia.zygotv.di.searchModule
import com.zygotecnologia.zygotv.di.seriesDetailsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class ZygoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ZygoApplication)

            modules(getList())
        }
    }
    private fun getList(): List<Module> = listOf(homeModule, seriesDetailsModule, searchModule)
}