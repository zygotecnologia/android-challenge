package com.zygotecnologia.zygotv.presentation.di.core

import com.zygotecnologia.zygotv.data.repository.TVRepositoryImpl
import com.zygotecnologia.zygotv.data.repository.datasource.TVCacheDataSource
import com.zygotecnologia.zygotv.data.repository.datasource.TVRemoteDataSource
import com.zygotecnologia.zygotv.domain.TVRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTvRepository(
        remote: TVRemoteDataSource,
        cache: TVCacheDataSource
    ) : TVRepository {
        return TVRepositoryImpl(remote, cache)
    }
}