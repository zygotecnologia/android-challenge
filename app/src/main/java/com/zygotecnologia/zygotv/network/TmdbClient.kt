package com.zygotecnologia.zygotv.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object TmdbClient {

    private val moshiAdapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun getInstance(): TmdbApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(TmdbApi.TMDB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiAdapter))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(OkHttpClient.Builder().build())
            .build()

        return retrofit.create(TmdbApi::class.java)
    }
}