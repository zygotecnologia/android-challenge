package com.zygotecnologia.zygotv.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zygotecnologia.zygotv.network.TmdbApi.Companion.TMDB_API_KEY
import com.zygotecnologia.zygotv.network.TmdbApi.Companion.TMDB_API_QUERY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object TmdbClient {

    private val moshiAdapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun getInstance(): TmdbApi {

        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.addInterceptor(TmdbInterceptor())

        val client = httpClientBuilder
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(TmdbApi.TMDB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiAdapter))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

        return retrofit.create(TmdbApi::class.java)
    }
}