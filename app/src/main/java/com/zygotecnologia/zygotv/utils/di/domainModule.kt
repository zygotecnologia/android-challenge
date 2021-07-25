package com.zygotecnologia.zygotv.utils.di

import com.zygotecnologia.zygotv.domain.interactor.GetGenresUseCase
import com.zygotecnologia.zygotv.domain.interactor.GetPopularShowsUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetGenresUseCase(get()) }
    single { GetPopularShowsUseCase(get()) }
}