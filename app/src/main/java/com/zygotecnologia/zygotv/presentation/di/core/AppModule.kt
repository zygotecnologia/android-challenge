package com.zygotecnologia.zygotv.presentation.di.core

import android.content.Context
import com.zygotecnologia.zygotv.presentation.di.detail.DetailSubComponent
import com.zygotecnologia.zygotv.presentation.di.home.HomeSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    subcomponents = [
        HomeSubComponent::class,
        DetailSubComponent::class
    ]
)
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}