package com.zygotecnologia.zygotv.view.shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.domain.entity.Genre
import com.zygotecnologia.zygotv.domain.entity.Show
import com.zygotecnologia.zygotv.domain.repository.ShowsRepository
import com.zygotecnologia.zygotv.utils.SingleLiveEvent

class ShowsViewModel(
    private val showsRepository: ShowsRepository
): ViewModel() {

    private val _showsContent = SingleLiveEvent<Pair<Show, List<Genre>>>()
    val showsContent : LiveData<Pair<Show, List<Genre>>> = _showsContent

    private val _loading = SingleLiveEvent<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _error = SingleLiveEvent<Boolean>()
    val error : LiveData<Boolean> = _error

    suspend fun loadShows() {
        try {

            _loading.value = true

            val shows = showsRepository.fetchPopularShows() ?: emptyList()

            val genres = showsRepository.fetchGenres()
                    ?.mapNotNull { genre ->
                        genre.id?.let { id ->
                            val genreShows = showsRepository.fetchShowsByGenresId(listOf(id.toString()))
                            genre.copy(shows = genreShows)
                        }
                    }
                    ?: emptyList()

            presentShows(shows, genres)
        } catch (e: Exception) {
            _error.value = true
        } finally {
            _loading.value = false
        }
    }

    private fun presentShows(shows: List<Show>, genres: List<Genre>) {
        shows.firstOrNull()?.let {
            _showsContent.value = Pair(it, genres)
        }
    }
}