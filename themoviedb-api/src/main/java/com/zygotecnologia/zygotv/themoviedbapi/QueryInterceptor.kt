package com.zygotecnologia.zygotv.themoviedbapi

import okhttp3.Interceptor

internal object QueryInterceptor : Interceptor {

    private const val API_QUERY = "api_key"
    private const val API_KEY = "27490b1bf49c0e5ffaa07dfd947e9605"

    override fun intercept(chain: Interceptor.Chain) = chain
        .request()
        .url()
        .newBuilder()
        .addQueryParameter(API_QUERY, API_KEY)
        .build().let { urlWithApiKeyQuery ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .url(urlWithApiKeyQuery)
                    .build()
            )
        }

}