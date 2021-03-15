package com.zygotecnologia.zygotv.di.servicefactory

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class AuthenticationInterceptor
    : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val request: Request = chain.request()
        val url: HttpUrl = request.url
            .newBuilder()
            .addQueryParameter("api_key", "27490b1bf49c0e5ffaa07dfd947e9605")
            .build()
        proceed(request.newBuilder().url(url).build())
    }
}