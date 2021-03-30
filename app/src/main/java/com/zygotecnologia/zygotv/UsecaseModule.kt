package com.zygotecnologia.zygotv

import com.zygotecnologia.zygotv.usecase.DashboardUseCase
import com.zygotecnologia.zygotv.usecase.DashboardUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class UsecaseModule {

    @Singleton
    @Binds
    abstract fun bindDashboardUseCase(usecaseModule: DashboardUseCaseImpl): DashboardUseCase
}