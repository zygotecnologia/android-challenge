package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.home.HomeViewModel
import com.zygotecnologia.zygotv.network.TmdbService
import com.zygotecnologia.zygotv.network.TmdbServiceFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<TmdbService> { TmdbServiceFactory().create() }

    viewModel { HomeViewModel(get()) }
}
