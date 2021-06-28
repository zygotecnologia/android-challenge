package com.zygotecnologia.zygotv.themoviedbapi

import okhttp3.Interceptor

internal object QueryInterceptor : Interceptor {

    private const val REGION_KEY = "region"
    private const val REGION_VALUE = "BR"

    override fun intercept(chain: Interceptor.Chain) = chain
        .request()
        .url()
        .newBuilder()
        .addQueryParameter(TheMovieDbAPI.API_QUERY, TheMovieDbAPI.API_KEY)
        .addQueryParameter(REGION_KEY, REGION_VALUE)
        .build().let { urlWithApiKeyQuery ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .url(urlWithApiKeyQuery)
                    .build()
            )
        }

}