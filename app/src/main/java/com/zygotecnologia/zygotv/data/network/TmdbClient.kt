package com.zygotecnologia.zygotv.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
            .baseUrl("${TMDB_BASE_URL}${TMDB_API_VERSION}")
            .addConverterFactory(MoshiConverterFactory.create(moshiAdapter))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

        return retrofit.create(TmdbApi::class.java)
    }

    private const val TMDB_API_VERSION = "/3/"
    private const val TMDB_BASE_URL = "https://api.themoviedb.org"
    const val TMDB_API_QUERY = "api_key"
}