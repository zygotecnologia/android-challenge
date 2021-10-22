package com.zygotecnologia.zygotv.home

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.tmdb.domain.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HomeViewModel(
    private val tmdbRepository: TmdbRepository
) : ViewModel() {

    private val _showsByGenre = flow {
        emit(tmdbRepository.getShowsByGenre())
    }.map { list ->
        list.filter { genre -> genre.shows.isEmpty() }
    }
    val showsByGenre: LiveData<List<GenreWithShows>> = _showsByGenre.asLiveData()
}
