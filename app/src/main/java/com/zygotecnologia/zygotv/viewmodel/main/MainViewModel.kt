package com.zygotecnologia.zygotv.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val tmdbApi: TmdbApi,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private lateinit var shows: List<Show>
    private lateinit var genres: List<Genre>

    private val _viewState = MutableLiveData<MainViewState>()
    var viewState: LiveData<MainViewState> = _viewState

    private val _mostPopularShow = MutableLiveData<Show>()
    var mostPopularShow: LiveData<Show> = _mostPopularShow

    fun fetchMovies() {
        CoroutineScope(coroutineContext).launch {
            _viewState.postValue(MainViewState.Loading)
            loadShows()
            _viewState.postValue(MainViewState.ShowList(shows, genres))
        }
    }

    private suspend fun loadShows() {
        genres =
            tmdbApi
                .fetchGenresAsync(TmdbApi.TMDB_API_KEY, "BR")
                ?.genres
                ?: emptyList()
        shows =
            tmdbApi
                .fetchPopularShowsAsync(TmdbApi.TMDB_API_KEY, "BR")
                ?.results
                ?.map { show ->
                    show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
                }
                ?: emptyList()

        populatePopularShow()
    }

    fun isConnected(checkConnection: () -> Boolean) {
        _viewState.value = MainViewState.ConnectionStatus(checkConnection.invoke())
    }

    private fun populatePopularShow() {
        _mostPopularShow.postValue(shows.first())
    }
}