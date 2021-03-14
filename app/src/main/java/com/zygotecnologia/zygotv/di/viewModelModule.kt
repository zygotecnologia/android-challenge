package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.presentation.home.MainViewModel
import com.zygotecnologia.zygotv.presentation.series.SeriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module(override = true) {
    viewModel { MainViewModel(get()) }
    viewModel { SeriesViewModel(get()) }
}