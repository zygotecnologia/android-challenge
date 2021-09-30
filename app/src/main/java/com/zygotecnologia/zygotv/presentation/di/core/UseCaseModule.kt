package com.zygotecnologia.zygotv.presentation.di.core

import com.zygotecnologia.zygotv.domain.TVRepository
import com.zygotecnologia.zygotv.domain.usecases.GetListOfGenreUseCase
import com.zygotecnologia.zygotv.domain.usecases.GetListOfShowUseCase
import com.zygotecnologia.zygotv.domain.usecases.GetShowUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetGenreUseCase(repository: TVRepository): GetListOfGenreUseCase {
        return GetListOfGenreUseCase(repository)
    }
    @Provides
    fun provideGetListOfShowUseCase(repository: TVRepository): GetListOfShowUseCase {
        return GetListOfShowUseCase(repository)
    }

    @Provides
    fun provideGetOfShowUseCase(repository: TVRepository): GetShowUseCase {
        return GetShowUseCase(repository)
    }
}