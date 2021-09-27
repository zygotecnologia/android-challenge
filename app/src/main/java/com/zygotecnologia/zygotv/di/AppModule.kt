package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.series.viewmodel.SeriesViewModel
import com.zygotecnologia.zygotv.repository.SeriesRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (seriesRepository : SeriesRepository) -> SeriesViewModel(seriesRepository) }
}