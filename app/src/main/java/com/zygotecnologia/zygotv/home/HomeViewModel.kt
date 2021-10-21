package com.zygotecnologia.zygotv.home

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbService
import kotlinx.coroutines.flow.flow

class HomeViewModel(
    private val tmdbService: TmdbService
) : ViewModel() {

    private val _shows = flow {
        emit(getShows())
    }
    val shows: LiveData<List<Show>> = _shows.asLiveData()

    private suspend fun getShows(): List<Show> {
        val genres = tmdbService.fetchGenresAsync(TmdbService.TMDB_API_KEY, "BR")
            ?.genres
            ?: emptyList()

        val shows = tmdbService.fetchPopularShowsAsync(TmdbService.TMDB_API_KEY, "BR")
            ?.results
            ?.map { show ->
                show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
            }
            ?: emptyList()

        return shows
    }
}
