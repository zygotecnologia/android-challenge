package com.zygotecnologia.zygotv.di.servicefactory

import com.zygotecnologia.zygotv.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpLoggingFactory {
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }


}