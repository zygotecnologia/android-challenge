package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.home.HomeViewModel
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbServiceFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<TmdbService> { TmdbServiceFactory().create() }

    viewModel { HomeViewModel(get()) }
}
