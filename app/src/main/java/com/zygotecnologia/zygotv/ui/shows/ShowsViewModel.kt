package com.zygotecnologia.zygotv.ui.shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbApi
import com.zygotecnologia.zygotv.network.TmdbClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShowsViewModel : ViewModel() {

    private val _showList = MutableLiveData<List<Show>>()
    val showList : LiveData<List<Show>> = _showList

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList : LiveData<List<Genre>> = _genreList

    private val _mostPopular = MutableLiveData<Show>()
    val mostPopularShow : LiveData<Show> = _mostPopular

    private val tmdbApi = TmdbClient.getInstance()

    suspend fun loadShows() {

        val genres =
            tmdbApi
                .fetchGenresAsync(TmdbApi.TMDB_API_KEY, "BR")
                ?.genres
                ?: emptyList()

        val shows =
            tmdbApi
                .fetchPopularShowsAsync(TmdbApi.TMDB_API_KEY, "BR")
                ?.results
                ?.map { show ->
                    show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
                }
                ?: emptyList()

        genres.forEach { genre ->
            genre.shows = shows.filter { it.genreIds?.contains(genre.id) ?: false }
        }

        withContext(Dispatchers.Main) {
            _genreList.value = genres
        }
    }

    suspend fun loadMostPopularShow() {
        val mostPopular =
            tmdbApi
                .fetchPopularShowsAsync(TmdbApi.TMDB_API_KEY, "BR")
                ?.results
                ?.firstOrNull()

        mostPopular?.let {
            withContext(Dispatchers.Main) {
                _mostPopular.value = it
            }
        }
    }
}