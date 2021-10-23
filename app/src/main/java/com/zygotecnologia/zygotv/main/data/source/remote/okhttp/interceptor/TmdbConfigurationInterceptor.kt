package com.zygotecnologia.zygotv.main.data.source.remote.okhttp.interceptor

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val TMDB_API_KEY = "27490b1bf49c0e5ffaa07dfd947e9605"
private const val REGION = "BR"

/**
 * Interceptor for configuring TMDB requests. It adds, by default, both the "api_key" and "region"
 * parameters to every request.
 */
class TmdbConfigurationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val url: HttpUrl = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", TMDB_API_KEY)
            .addQueryParameter("region", REGION)
            .build()

        val request: Request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
