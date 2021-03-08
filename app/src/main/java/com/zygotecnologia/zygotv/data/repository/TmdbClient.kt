package com.zygotecnologia.zygotv.data.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zygotecnologia.zygotv.data.remote.TmdbApi
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object TmdbClient {

    fun provideQueryApiKey() : Interceptor = Interceptor {
        val request: Request = it.request()
        val originalHttpRequest: HttpUrl = request.url
        val url: HttpUrl =
            originalHttpRequest.newBuilder().addQueryParameter("api_key", "27490b1bf49c0e5ffaa07dfd947e9605").build()
        val requestBuilder: Request.Builder = request.newBuilder()
            .url(url)
        val newRequest = requestBuilder.build()
        it.proceed(newRequest)
    }

    fun provideLoggingApi() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun provideHttpClient(logging : HttpLoggingInterceptor , queryKey : Interceptor) : OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(queryKey)
        addInterceptor(logging)
    }.build()

    private val moshiAdapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    fun provideRetrofit(httpClient : OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(TmdbApi.TMDB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiAdapter))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .build()

    fun provideApi(retrofit : Retrofit) : TmdbApi = retrofit.create(
        TmdbApi::class.java)

}