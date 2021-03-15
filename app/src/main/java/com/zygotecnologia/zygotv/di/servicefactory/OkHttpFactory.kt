package com.zygotecnologia.zygotv.di.servicefactory

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpFactory {
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,  interceptor: NetworkConnectionInterceptor): OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(AuthenticationInterceptor())
        .build()

}