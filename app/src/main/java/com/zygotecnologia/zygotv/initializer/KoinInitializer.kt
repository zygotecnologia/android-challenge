package com.zygotecnologia.zygotv.initializer

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.di.repositoryModule
import com.zygotecnologia.zygotv.di.serviceModule
import com.zygotecnologia.zygotv.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinInitializer : Initializer<Koin> {
    override fun create(context: Context): Koin = context
        .startKoinIfNeeded()
        .also { Log.d("Initializer","Koin initialized") }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf()
}

/**
 * Start koin if global KoinContext is null.
 * @return [Koin] instance.
 */
fun Context.startKoinIfNeeded(): Koin {
    return GlobalContext.getOrNull() ?: startKoin {
        // use AndroidLogger as Koin Logger
        androidLogger(level = if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)

        // use the Android context given there
        androidContext(applicationContext)

        modules(
            listOf(serviceModule, repositoryModule, viewModelModule)
        )
    }.koin
}