package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.repository.ShowsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        ShowsRepository(get())
    }
}