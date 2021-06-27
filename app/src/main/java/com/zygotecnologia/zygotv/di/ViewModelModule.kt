package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.presentation.detail.DetailViewModel
import com.zygotecnologia.zygotv.presentation.search.SearchViewModel
import com.zygotecnologia.zygotv.presentation.shows.ShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ShowsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}