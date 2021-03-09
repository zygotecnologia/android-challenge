package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.ui.series.DetailsSeriesFragment
import com.zygotecnologia.zygotv.ui.series.HomeSeriesFragment
import com.zygotecnologia.zygotv.ui.series.SearchResultFragment
import com.zygotecnologia.zygotv.viewmodel.DetailsSeriesViewModel
import com.zygotecnologia.zygotv.viewmodel.HomeSeriesViewModel
import com.zygotecnologia.zygotv.data.repository.TmdbClient
import com.zygotecnologia.zygotv.data.repository.ZygoRepository
import com.zygotecnologia.zygotv.viewmodel.SearchResultViewModel
import com.zygotecnologia.zygotv.viewmodel.SearchViewModel
import com.zygotecnologia.zygotv.utils.ConnectivityManager
import org.koin.dsl.module

val repositoryModule = module {
    factory { ZygoRepository(get()) }
}

val connectionModule = module {
    factory { ConnectivityManager(get()) }
}

val fragmentModules = module {
    factory { HomeSeriesFragment() }
    factory { DetailsSeriesFragment() }
    factory { SearchResultFragment() }
}

val vmModules = module {
    factory { HomeSeriesViewModel(get()) }
    factory { DetailsSeriesViewModel(get()) }
    factory { SearchViewModel() }
    factory { SearchResultViewModel(get()) }
}

val networkModule = module {
    factory { TmdbClient.provideQueryApiKey()}
    factory { TmdbClient.provideLoggingApi() }
    factory { TmdbClient.provideApi(get()) }
    factory { TmdbClient.provideHttpClient(get(),get()) }
    single { TmdbClient.provideRetrofit(get()) }
}
