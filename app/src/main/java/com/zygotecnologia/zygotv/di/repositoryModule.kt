package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.service.remote.repository.season.SeasonRepository
import com.zygotecnologia.zygotv.service.remote.repository.season.SeasonRepositoryImp
import com.zygotecnologia.zygotv.service.remote.repository.serie.SerieRepository
import com.zygotecnologia.zygotv.service.remote.repository.serie.SerieRepositoryImp
import org.koin.dsl.module


val repositoryModule = module(override = true) {
    factory  <SerieRepository>{ SerieRepositoryImp(get()) }
    factory <SeasonRepository>{ SeasonRepositoryImp(get()) }

}