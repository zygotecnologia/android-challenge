package com.zygotecnologia.zygotv.details.presentation

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.dataOrNull
import com.zygotecnologia.zygotv.tmdb.domain.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DetailsViewModel(
    private val showId: Int,
    private val tmdbRepository: TmdbRepository
) : ViewModel() {

    private val _showNetworkError = MutableLiveData(false)
    val showNetworkError: LiveData<Boolean> = _showNetworkError

    private val _show = flow {
        val show = tmdbRepository.getShow(showId).dataOrNull()

        if (show != null) emit(show)
        _showNetworkError.postValue(show == null)
    }
    val show: LiveData<Show> = _show.asLiveData()
}
