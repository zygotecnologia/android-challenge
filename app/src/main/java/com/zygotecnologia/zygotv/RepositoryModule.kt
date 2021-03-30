package com.zygotecnologia.zygotv

import com.zygotecnologia.zygotv.datasource.RemoteDataSource
import com.zygotecnologia.zygotv.datasource.RemoteDataSourceImpl
import com.zygotecnologia.zygotv.repository.DashboardMoviesRepository
import com.zygotecnologia.zygotv.repository.DashboardMoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindDashboardMoviesRepository(repository: DashboardMoviesRepositoryImpl): DashboardMoviesRepository

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource
}