package com.zygotecnologia.zygotv.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object TmdbRetrofitClient {

    fun build(baseUrl: String, client: OkHttpClient, moshiConverter: MoshiConverterFactory) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            //.addConverterFactory(moshiConverter)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}