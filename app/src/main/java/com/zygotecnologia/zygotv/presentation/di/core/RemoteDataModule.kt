package com.zygotecnologia.zygotv.presentation.di.core

import com.zygotecnologia.zygotv.data.api.ApiService
import com.zygotecnologia.zygotv.data.repository.datasource.TVRemoteDataSource
import com.zygotecnologia.zygotv.data.repository.datasourceImpl.TVRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {

    @Singleton
    @Provides
    fun provideImageRemoteDataSource(apiService: ApiService): TVRemoteDataSource {
        return TVRemoteDataSourceImpl(
            apiService, apiKey
        )
    }

}