package com.zygotecnologia.zygotv

import com.zygotecnologia.zygotv.api.TmdbApi
import com.zygotecnologia.zygotv.api.TmdbClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object TmdbModule {

    @Provides
    @Singleton
    fun provideTmb(): TmdbApi = TmdbClient.getInstance()
}