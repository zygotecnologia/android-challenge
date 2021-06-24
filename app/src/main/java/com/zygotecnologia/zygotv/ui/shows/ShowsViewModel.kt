package com.zygotecnologia.zygotv.ui.shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.ShowsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShowsViewModel(
    private val showsRepository: ShowsRepository
) : ViewModel() {

//    private val _showList = MutableLiveData<List<Show>>()
//    val showList : LiveData<List<Show>> = _showList

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList : LiveData<List<Genre>> = _genreList

    private val _mostPopular = MutableLiveData<Show>()
    val mostPopularShow : LiveData<Show> = _mostPopular

    private val _selectedShow = MutableLiveData<Int>()
    val selectedShow : LiveData<Int> = _selectedShow

    suspend fun loadShows() {

        val genres =
            showsRepository
                .fetchGenres()
                ?.genres
                ?: emptyList()

        val shows =
            showsRepository
                .fetchPopularShows()
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
            showsRepository
                .fetchPopularShows()
                ?.results
                ?.firstOrNull()

        mostPopular?.let {
            withContext(Dispatchers.Main) {
                _mostPopular.value = it
            }
        }
    }

    fun onShowClicked(showId: Int) {
        _selectedShow.value = showId
    }
}