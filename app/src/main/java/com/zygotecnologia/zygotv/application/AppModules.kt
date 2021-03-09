package com.zygotecnologia.zygotv.application

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.BuildConfig.DEBUG
import com.zygotecnologia.zygotv.database.MoviesDAO
import com.zygotecnologia.zygotv.database.MoviesDatabase
import com.zygotecnologia.zygotv.network.TmdbApi
import com.zygotecnologia.zygotv.repository.MoviesRepository
import com.zygotecnologia.zygotv.viewmodel.MoviesViewModel
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


val apiModule = module {

    fun provideMoviesApi(retrofit: Retrofit): TmdbApi {
        return retrofit.create(TmdbApi::class.java)
    }
    single { provideMoviesApi(get()) }

}

val repositoryModule = module {
    fun provideNewsRepository(api: TmdbApi, dao: MoviesDAO): MoviesRepository {
        return MoviesRepository(api, dao)
    }
    single { provideNewsRepository(get(), get()) }
}

val networkModule = module {
    val connectTimeout: Long = 40// 20s
    val readTimeout: Long = 40 // 20s

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        okHttpClientBuilder.addInterceptor { chain ->
            var original = chain.request()

            val url: HttpUrl = original.url.newBuilder().addQueryParameter("api_key", BuildConfig.ApiKey).addQueryParameter("region", TmdbApi.REGION).build()
            original = original.newBuilder().url(url).build()

            chain.proceed(original)
        }

        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(TmdbApi.TMDB_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build()
    }

    single { provideHttpClient() }
    single {
        provideRetrofit(get())
    }
}


val databaseModule = module {

    fun provideDatabase(application: Application): MoviesDatabase {
        return Room.databaseBuilder(application, MoviesDatabase::class.java, "news-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideNewsDao(database: MoviesDatabase): MoviesDAO {
        return database.genresDAO()
    }

    single { provideDatabase(androidApplication()) }
    single { provideNewsDao(get()) }
}

val roomTestModule = module(override = true) {
    single {
        Room.inMemoryDatabaseBuilder(get(), MoviesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}

val viewModelModule = module {

    viewModel { MoviesViewModel(repository = get()) }

}