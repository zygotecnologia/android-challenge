package com.zygotecnologia.zygotv.presentation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zygotecnologia.zygotv.domain.usecases.GetListOfGenreUseCase
import com.zygotecnologia.zygotv.domain.usecases.GetListOfShowUseCase

class HomeViewModelFactory(
    private val getListOfGenreUseCase: GetListOfGenreUseCase,
    private val getListOfShowUseCase: GetListOfShowUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(
                getListOfGenreUseCase,
                getListOfShowUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}