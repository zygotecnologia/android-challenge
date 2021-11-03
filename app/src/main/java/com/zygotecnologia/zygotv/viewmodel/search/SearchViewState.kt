package com.zygotecnologia.zygotv.viewmodel.search

import com.zygotecnologia.zygotv.model.Show

sealed class SearchViewState {
    object Loading : SearchViewState()
    data class SearchSuccess(val showList: List<Show>) : SearchViewState()
}