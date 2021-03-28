package com.zygotecnologia.zygotv.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.network.TmdbApi
import com.zygotecnologia.zygotv.network.TmdbConfigInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/* NetworkModule
 * Provides every object to create an API Services
 * Provides all API Services
 */

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    //Provides a generic moshi adapter
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    //Provides a generic OkHttpClient with log
    @Provides
    fun providesOkHttpClient(): OkHttpClient.Builder {
        val baseClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            baseClient.addInterceptor(logInterceptor)
        }

        return baseClient
    }

    //Provides the TmdbApiService
    @Provides
    @Singleton
    fun providesTmdbApiService(moshi: Moshi, clientBuilder: OkHttpClient.Builder): TmdbApi {
        //Additional interceptors used only in this Api Service
        val client = clientBuilder
            .addInterceptor(TmdbConfigInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

        return retrofit.create(TmdbApi::class.java)
    }

}