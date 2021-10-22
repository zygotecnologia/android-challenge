package com.zygotecnologia.zygotv.tmdb.data.source.remote.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.NetworkResultCallAdapterFactory
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
            .addCallAdapterFactory(NetworkResultCallAdapterFactory())
            .client(OkHttpClient.Builder().build())
            .build()

        return retrofit.create(TmdbService::class.java)
    }
}