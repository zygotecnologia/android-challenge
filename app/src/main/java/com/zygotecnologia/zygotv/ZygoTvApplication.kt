package com.zygotecnologia.zygotv

import android.app.Application
import com.zygotecnologia.zygotv.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ZygoTvApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ZygoTvApplication)
            modules(
                listOf(
                    repositoryModule, connectionModule, fragmentModules, vmModules, networkModule
                )
            )
        }
    }


}