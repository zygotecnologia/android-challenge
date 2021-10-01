package com.zygotecnologia.zygotv.presentation.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zygotecnologia.zygotv.domain.usecases.GetShowUseCase

class DetailViewModelFactory(
    private val getShowUseCase: GetShowUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(
                getShowUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}