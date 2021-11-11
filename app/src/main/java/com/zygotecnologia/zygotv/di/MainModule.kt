package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.data.network.TmdbClient
import com.zygotecnologia.zygotv.data.repository.TmdbDataSource
import com.zygotecnologia.zygotv.ui.details.viewmodel.SeriesDetailsViewModel
import com.zygotecnologia.zygotv.ui.home.viewmodel.HomeViewModel
import com.zygotecnologia.zygotv.ui.search.viewmodel.SearchViewModel
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

val searchModule = module {
    viewModel {
        SearchViewModel(repository = TmdbDataSource(TmdbClient))
    }
}