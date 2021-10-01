package com.zygotecnologia.zygotv.presentation.di.core

import com.zygotecnologia.zygotv.presentation.di.detail.DetailSubComponent
import com.zygotecnologia.zygotv.presentation.di.home.HomeSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        CacheDataModule::class,
        RemoteDataModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {

    fun homeSubComponent(): HomeSubComponent.Factory
    fun detailSubComponent() : DetailSubComponent.Factory
}