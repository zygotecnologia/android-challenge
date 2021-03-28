package com.zygotecnologia.zygotv.model.network

import com.zygotecnologia.zygotv.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TmdbConfigInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newUrl = originalRequest
            .url
            .newBuilder()
            .addQueryParameter(BuildConfig.TMDB_API_KEY_QUERY, BuildConfig.TMDB_API_KEY_VALUE)
            .addQueryParameter("region", BuildConfig.TMDB_BASE_REGION)
            .build()

        val newRequest = originalRequest
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

}