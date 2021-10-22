package com.zygotecnologia.zygotv.home

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.tmdb.domain.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository
import kotlinx.coroutines.flow.flow

class HomeViewModel(
    private val tmdbRepository: TmdbRepository
) : ViewModel() {

    private val _showsByGenre = flow {
        emit(tmdbRepository.getShowsByGenre())
    }
    val showsByGenre: LiveData<List<GenreWithShows>> = _showsByGenre.asLiveData()
}
