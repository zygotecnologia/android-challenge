package com.zygotecnologia.zygotv.presentation.di.home

import com.zygotecnologia.zygotv.domain.usecases.GetListOfGenreUseCase
import com.zygotecnologia.zygotv.domain.usecases.GetListOfShowUseCase
import com.zygotecnologia.zygotv.presentation.ui.home.viewmodel.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @HomeScope
    @Provides
    fun provideHomeViewModelFactory(
        getListOfGenreUseCase: GetListOfGenreUseCase,
        getListOfShowUseCase: GetListOfShowUseCase
    ): HomeViewModelFactory {
        return HomeViewModelFactory(
            getListOfGenreUseCase,
            getListOfShowUseCase
        )
    }
}