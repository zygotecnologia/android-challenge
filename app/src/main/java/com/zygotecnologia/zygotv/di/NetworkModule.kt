package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.data.network.TmdbClient
import org.koin.dsl.module

val networkModule = module {

    single {
        TmdbClient.getInstance()
    }
}