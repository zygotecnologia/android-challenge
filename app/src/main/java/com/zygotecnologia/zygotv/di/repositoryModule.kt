package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.service.remote.repository.MainRepository
import org.koin.dsl.module


val repositoryModule = module(override = true) {
    single <MainRepository>{ MainRepository(get()) }

}