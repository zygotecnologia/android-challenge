package com.zygotecnologia.zygotv.presentation.di.detail

import com.zygotecnologia.zygotv.domain.usecases.GetShowUseCase
import com.zygotecnologia.zygotv.presentation.ui.detail.viewmodel.DetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @DetailScope
    @Provides
    fun provideDetailViewModelFactory(getShowUseCase: GetShowUseCase) : DetailViewModelFactory {
        return  DetailViewModelFactory(getShowUseCase)
    }
}