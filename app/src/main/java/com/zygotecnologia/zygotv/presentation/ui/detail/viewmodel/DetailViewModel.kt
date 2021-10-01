package com.zygotecnologia.zygotv.presentation.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zygotecnologia.zygotv.domain.usecases.GetShowUseCase

class DetailViewModel(
    private val getShowUseCase: GetShowUseCase
) : ViewModel()  {

    fun getShowById(tvId: Int) = liveData {
        emit(getShowUseCase.execute(tvId))
    }
}