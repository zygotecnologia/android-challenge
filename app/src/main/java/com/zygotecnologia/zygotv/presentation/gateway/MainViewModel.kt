package com.zygotecnologia.zygotv.presentation.gateway

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.domain.interactor.GetGenresUseCase
import com.zygotecnologia.zygotv.domain.interactor.GetPopularShowsUseCase
import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.domain.model.Show
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val getGenresUseCase: GetGenresUseCase,
    private val getPopularShowsUseCase: GetPopularShowsUseCase
) : ViewModel() {
    private val _popularShows = MutableLiveData<List<Show>>()
    val popularShows: LiveData<List<Show>> = _popularShows

    fun loadShows() {
        viewModelScope.launch {
            getGenresUseCase.execute().collect {
                loadPopularShows(it)
            }
        }
    }

    private fun loadPopularShows(genres: List<Genre>) {
        viewModelScope.launch {
            getPopularShowsUseCase.execute(genres).collect {
                _popularShows.value = it
            }
        }
    }
}