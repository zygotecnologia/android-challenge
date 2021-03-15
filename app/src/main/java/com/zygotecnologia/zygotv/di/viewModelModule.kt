package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.presentation.season.SeasonViewModel
import com.zygotecnologia.zygotv.presentation.serie.SeriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module(override = true) {
    viewModel { SeriesViewModel(get()) }
    viewModel { SeasonViewModel(get()) }
}