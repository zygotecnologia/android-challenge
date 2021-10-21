package com.zygotecnologia.zygotv.home

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService
import kotlinx.coroutines.flow.flow

class HomeViewModel(
    private val tmdbService: TmdbService
) : ViewModel() {

    private val _shows = flow {
        emit(getShows())
    }
    val shows: LiveData<List<ShowResponse>> = _shows.asLiveData()

    private suspend fun getShows(): List<ShowResponse> {
        val genres = tmdbService
            .fetchGenresAsync(TmdbService.TMDB_API_KEY, "BR")
            .genreResponses

        return tmdbService
            .fetchPopularShowsAsync(TmdbService.TMDB_API_KEY, "BR")
            .results
    }
}
