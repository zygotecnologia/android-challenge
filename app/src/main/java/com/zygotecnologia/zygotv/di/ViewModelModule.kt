package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.ui.detail.DetailViewModel
import com.zygotecnologia.zygotv.ui.shows.ShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ShowsViewModel() }
    viewModel { DetailViewModel() }
}