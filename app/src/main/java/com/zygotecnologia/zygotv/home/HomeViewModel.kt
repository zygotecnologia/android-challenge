package com.zygotecnologia.zygotv.home

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository
import kotlinx.coroutines.flow.flow

class HomeViewModel(
    private val tmdbRepository: TmdbRepository
) : ViewModel() {

    private val _shows = flow {
        emit(tmdbRepository.getShows())
    }
    val shows: LiveData<List<Show>> = _shows.asLiveData()
}
