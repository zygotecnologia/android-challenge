package com.zygotecnologia.zygotv.presentation.di.core

import com.zygotecnologia.zygotv.data.repository.datasource.TVCacheDataSource
import com.zygotecnologia.zygotv.data.repository.datasourceImpl.TVCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideTVCacheDataSource(): TVCacheDataSource {
        return TVCacheDataSourceImpl()
    }
}