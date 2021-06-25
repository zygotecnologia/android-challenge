package com.zygotecnologia.zygotv.ui.shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.ShowsRepository
import com.zygotecnologia.zygotv.utils.SingleLiveEvent

class ShowsViewModel(
    private val showsRepository: ShowsRepository
): ViewModel() {

    private val _genreList = SingleLiveEvent<List<Genre>>()
    val genreList : LiveData<List<Genre>> = _genreList

    private val _mostPopular = SingleLiveEvent<Show>()
    val mostPopularShow : LiveData<Show> = _mostPopular

    private val _selectedShow = SingleLiveEvent<Int>()
    val selectedShow : LiveData<Int> = _selectedShow

    private val _loading = SingleLiveEvent<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _error = SingleLiveEvent<Boolean>()
    val error : LiveData<Boolean> = _error

    suspend fun loadShows() {
        try {

            _loading.value = true

            val shows =
                showsRepository
                    .fetchPopularShows()
                    ?.results
                    ?: emptyList()

            val genres =
                showsRepository
                    .fetchGenres()
                    ?.genres
                    ?.map { genre ->
                        genre.copy(shows = shows.filter { it.genreIds?.contains(genre.id) ?: false })
                    }
                    ?.filter { genre -> !genre.shows.isNullOrEmpty() }
                    ?: emptyList()

            presentShows(shows, genres)
        } catch (e: Exception) {
            _loading.value = false
            _error.value = true
        }
    }

    fun onShowClicked(showId: Int) {
        _selectedShow.value = showId
    }

    private fun presentShows(
        shows: List<Show>,
        genres: List<Genre>
    ) {
        _loading.value = false
        _genreList.value = genres
        shows.firstOrNull()?.let {
            _mostPopular.value = it
        }
    }
}