package com.zygotecnologia.zygotv.viewmodel.main

import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show

sealed class MainViewState {
    object ConnectionIsOffline : MainViewState()
    object Loading : MainViewState()
    data class ShowList(val showList: List<Show>, val genreList: List<Genre>) : MainViewState()
}