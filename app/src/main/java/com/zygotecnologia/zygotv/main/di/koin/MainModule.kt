package com.zygotecnologia.zygotv.main.di.koin

import org.koin.dsl.module
import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.main.viewModel.MainViewModel
import com.zygotecnologia.zygotv.network.api.repository.ApiRepository
import com.zygotecnologia.zygotv.network.api.repository.TmdbApiRepository
import com.zygotecnologia.zygotv.network.api.service.ApiService
import com.zygotecnologia.zygotv.network.retrofit.OkHttpClientFactory
import com.zygotecnologia.zygotv.network.retrofit.TmdbRetrofitClient
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val CoreModule = module {

    // JSON
    single { MoshiConverterFactory.create() }

    // Network
    factory<Interceptor> { HttpLoggingInterceptor() }
    single { OkHttpClientFactory.build(get(), androidContext()) }
    single { TmdbRetrofitClient.build(BuildConfig.BASE_URL + "/" + BuildConfig.API_VERSION + "/", get(), get()) }

    // API
    single { get<Retrofit>().create(ApiService::class.java) }
    factory<ApiRepository> { TmdbApiRepository(get(), get()) }

    // ViewModels
    viewModel { MainViewModel(get()) }

}