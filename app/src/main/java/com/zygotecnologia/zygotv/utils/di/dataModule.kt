package com.zygotecnologia.zygotv.utils.di

import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.data.repository.ZygoRepositoryImpl
import com.zygotecnologia.zygotv.domain.repository.ZygoRepository
import org.koin.dsl.module

val dataModule = module {
    single<TmdbApi> { com.zygotecnologia.zygotv.data.network.TmdbClient.getInstance() }
    single<ZygoRepository> { ZygoRepositoryImpl(get()) }
}