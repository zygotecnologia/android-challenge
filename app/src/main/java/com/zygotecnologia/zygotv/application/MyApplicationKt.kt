package com.zygotecnologia.zygotv.application

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


open class MyApplicationKt : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplicationKt)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    databaseModule,
                    networkModule,
                    apiModule,
                )
            )
        }
    }
}