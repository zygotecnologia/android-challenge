package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.data.network.TmdbClient
import com.zygotecnologia.zygotv.data.repository.TmdbDataSource
import com.zygotecnologia.zygotv.ui.details.SeriesDetailsViewModel
import com.zygotecnologia.zygotv.ui.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(repository = TmdbDataSource(TmdbClient))
    }

}

val seriesDetailsModule = module {
    viewModel {
        SeriesDetailsViewModel(repository = TmdbDataSource(TmdbClient))
    }
}