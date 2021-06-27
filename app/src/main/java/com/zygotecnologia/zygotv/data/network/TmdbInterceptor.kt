package com.zygotecnologia.zygotv.data.network

import com.zygotecnologia.zygotv.utils.secret.Keys
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class TmdbInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", Keys.apiKey())
            .addQueryParameter("language", Locale.getDefault().toLanguageTag() )
            .build()

        val requestBuilder = original.newBuilder().url(url)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }

}