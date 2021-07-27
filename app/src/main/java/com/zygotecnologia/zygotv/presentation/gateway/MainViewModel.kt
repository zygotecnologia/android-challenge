package com.zygotecnologia.zygotv.presentation.gateway

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.domain.interactor.GetGenresUseCase
import com.zygotecnologia.zygotv.domain.interactor.GetPopularShowsUseCase
import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.presentation.model.ShowResultView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val getGenresUseCase: GetGenresUseCase,
    private val getPopularShowsUseCase: GetPopularShowsUseCase
) : ViewModel() {
    private val _popularShows = MutableLiveData<ShowResultView>()
    val popularShows: LiveData<ShowResultView> = _popularShows
    private val showByGender = mutableMapOf<Genre, MutableList<Show>> ()

    fun loadShows() {
        viewModelScope.launch {
            getGenresUseCase.execute().collect {
                setGenres(it)
                loadPopularShows(it)
            }
        }
    }

    private fun loadPopularShows(genres: List<Genre>) {
        viewModelScope.launch {
            getPopularShowsUseCase.execute(genres).collect {
                setPopularShows(it)
                _popularShows.value = ShowResultView(getMostPopularShow(it), genres, showByGender)
            }
        }
    }

    private fun setGenres(genres: List<Genre>) {
        genres.forEach {
            showByGender[it] = mutableListOf()
        }
    }

    private fun setPopularShows(shows: List<Show>) {
        shows.forEach { show ->
            show.genres?.forEach {  genre ->
                val showList = showByGender[genre]
                showList?.add(show)
                showList?.let {
                    showByGender[genre] = it
                }
            }
        }
        showByGender.values.removeIf{ it.isEmpty() }
    }

    private fun getMostPopularShow(shows: List<Show>): Show? {
        return shows.maxByOrNull{ it.voteCount ?: 0 }
    }
}