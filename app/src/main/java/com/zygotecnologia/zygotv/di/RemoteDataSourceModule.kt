package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.data.remote.ShowsRemoteDataSource
import com.zygotecnologia.zygotv.data.remote.ShowsRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    factory { ShowsRemoteDataSourceImpl(get()) as ShowsRemoteDataSource }
}