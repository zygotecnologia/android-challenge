package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.details.presentation.DetailsViewModel
import com.zygotecnologia.zygotv.home.presentation.HomeViewModel
import com.zygotecnologia.zygotv.tmdb.data.TmdbRepositoryImpl
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbServiceFactory
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<TmdbService> { TmdbServiceFactory().create() }

    single<TmdbRepository> { TmdbRepositoryImpl(get()) }

    viewModel { HomeViewModel(get()) }

    viewModel { (showId : Int) -> DetailsViewModel(showId, get()) }
}
