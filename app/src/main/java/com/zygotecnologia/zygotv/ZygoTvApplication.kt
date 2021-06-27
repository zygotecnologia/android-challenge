package com.zygotecnologia.zygotv

import android.app.Application
import com.zygotecnologia.zygotv.di.networkModule
import com.zygotecnologia.zygotv.di.remoteDataSourceModule
import com.zygotecnologia.zygotv.di.repositoryModule
import com.zygotecnologia.zygotv.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class ZygoTvApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setupDependencyInjection()
    }

    private fun setupDependencyInjection() {
        startKoin {
            androidLogger()
            androidContext(this@ZygoTvApplication)
            modules(getModules())
        }
    }

    private fun getModules(): List<Module> = listOf(
        viewModelModule,
        repositoryModule,
        remoteDataSourceModule,
        networkModule
    )
}