package com.zygotecnologia.zygotv.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object TmdbClient {

    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val queryInterceptor: Interceptor = Interceptor {
        val request: Request = it.request()
        val originalHttpRequest: HttpUrl = request.url
        val url: HttpUrl =
            originalHttpRequest.newBuilder().addQueryParameter("api_key", "27490b1bf49c0e5ffaa07dfd947e9605").build()
        val requestBuilder: Request.Builder = request.newBuilder()
            .url(url)
        val newRequest = requestBuilder.build()
        it.proceed(newRequest)
    }

    private val moshiAdapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(logging)
        addInterceptor(queryInterceptor)
    }

    fun getInstance(): TmdbApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(TmdbApi.TMDB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiAdapter))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client.build())
            .build()

        return retrofit.create(TmdbApi::class.java)
    }
}