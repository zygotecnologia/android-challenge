package com.zygotecnologia.zygotv.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TmdbServiceFactory {

    private val moshiAdapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun create(): TmdbService {
        val retrofit = Retrofit.Builder()
            .baseUrl(TmdbService.TMDB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiAdapter))
            .client(OkHttpClient.Builder().build())
            .build()

        return retrofit.create(TmdbService::class.java)
    }
}