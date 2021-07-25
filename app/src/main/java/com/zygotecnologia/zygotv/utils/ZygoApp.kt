package com.zygotecnologia.zygotv.utils

import android.app.Application
import com.zygotecnologia.zygotv.utils.di.dataModule
import com.zygotecnologia.zygotv.utils.di.domainModule
import com.zygotecnologia.zygotv.utils.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ZygoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ZygoApp)
            androidFileProperties()
            modules(listOf(
                domainModule,
                dataModule,
                presentationModule
            ))
        }
    }
}