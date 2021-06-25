package com.zygotecnologia.zygotv.ui.shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.ShowsRepository
import com.zygotecnologia.zygotv.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

            val genres = showsRepository.fetchGenres()?.genres ?: emptyList()
            val shows =
                showsRepository
                    .fetchPopularShows()
                    ?.results
                    ?.map { show ->
                        show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
                    }
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

    private suspend fun presentShows(
        shows: List<Show>,
        genres: List<Genre>
    ) {
        val mostPopularShow = shows.firstOrNull()

        genres.forEach { genre ->
            genre.shows = shows.filter { it.genreIds?.contains(genre.id) ?: false }
        }

        withContext(Dispatchers.Main) {
            _loading.value = false
            _genreList.value = genres
            mostPopularShow?.let {
                _mostPopular.value = it
            }
        }
    }

}