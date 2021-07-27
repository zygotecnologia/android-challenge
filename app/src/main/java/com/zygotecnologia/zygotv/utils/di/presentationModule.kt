package com.zygotecnologia.zygotv.utils.di

import com.zygotecnologia.zygotv.presentation.gateway.MainViewModel
import org.koin.dsl.module

val presentationModule = module {
    single { MainViewModel(get(), get()) }
}