package com.zygotecnologia.zygotv.home

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.dataOrNull
import com.zygotecnologia.zygotv.tmdb.domain.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HomeViewModel(
    private val tmdbRepository: TmdbRepository
) : ViewModel() {

    private val _showNetworkError = MutableLiveData(false)
    val showNetworkError: LiveData<Boolean> = _showNetworkError

    private val _showsByGenre = flow {
        val showsByGenre = tmdbRepository.getShowsByGenre().dataOrNull()

        emit(showsByGenre ?: emptyList())
        _showNetworkError.postValue(showsByGenre == null)
    }.map { list ->
        list.filter { genre -> genre.shows.isNotEmpty() }
    }
    val showsByGenre: LiveData<List<GenreWithShows>> = _showsByGenre.asLiveData()

    private val _mostPopularShow = flow {
        val show = tmdbRepository.getMostPopularShow().dataOrNull()

        if (show != null) emit(show)
        _showNetworkError.postValue(show == null)
    }
    val mostPopularShow: LiveData<Show> = _mostPopularShow.asLiveData()
}
