package com.zygotecnologia.zygotv.themoviedbapi

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zygotecnologia.zygotv.themoviedbapi.genre.tv.GenresListAPI
import com.zygotecnologia.zygotv.themoviedbapi.tv.TvShowByIdAPI
import com.zygotecnologia.zygotv.themoviedbapi.tv.popular.PopularTvShowsAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object TheMovieDbAPI {

    internal const val API_VERSION = "3"

    private const val BASE_URL = "https://api.themoviedb.org"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    const val API_KEY = "27490b1bf49c0e5ffaa07dfd947e9605"
    const val API_QUERY = "api_key"

    val tvShowByIdAPI: TvShowByIdAPI get() = make()
    val popularTvShowsAPI: PopularTvShowsAPI get() = make()
    val genresListAPI: GenresListAPI get() = make()

    private val moshiAdapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private inline fun <reified T : Any> make() = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshiAdapter))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(
            OkHttpClient
                .Builder()
                .addInterceptor(QueryInterceptor)
                .build()
        )
        .build()
        .create(T::class.java)
}