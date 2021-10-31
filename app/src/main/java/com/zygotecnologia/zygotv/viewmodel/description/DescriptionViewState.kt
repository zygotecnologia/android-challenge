package com.zygotecnologia.zygotv.viewmodel.description

import com.zygotecnologia.zygotv.model.Show

sealed class DescriptionViewState {
    object Loading : DescriptionViewState()
    data class ShowDescription(val showDescription: Show) : DescriptionViewState()
    object Error : DescriptionViewState()
}