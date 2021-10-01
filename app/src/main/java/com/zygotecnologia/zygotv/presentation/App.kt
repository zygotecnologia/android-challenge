package com.zygotecnologia.zygotv.presentation

import android.app.Application
import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.presentation.di.Injector
import com.zygotecnologia.zygotv.presentation.di.core.*
import com.zygotecnologia.zygotv.presentation.di.detail.DetailSubComponent
import com.zygotecnologia.zygotv.presentation.di.home.HomeSubComponent

class App : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.apiKey))
            .build()
    }

    override fun createHomeSubComponent(): HomeSubComponent =
        appComponent.homeSubComponent().create()

    override fun createDetailSubComponent(): DetailSubComponent =
        appComponent.detailSubComponent().create()

}