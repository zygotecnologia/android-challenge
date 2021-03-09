package com.zygotecnologia.zygotv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.Episodes
import com.zygotecnologia.zygotv.model.GenreCategory
import com.zygotecnologia.zygotv.model.RequestStatus
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.MoviesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await
import java.util.Comparator


class MoviesViewModel(
    private val repository: MoviesRepository
) : ViewModel() {
    private val _statusPopularShows = MutableLiveData<RequestStatus>()
    val statusPopularShows: LiveData<RequestStatus>
        get() = _statusPopularShows

    private val _popularShows = MutableLiveData<List<GenreCategory>>()
    val popularShows: LiveData<List<GenreCategory>>
        get() = _popularShows

    private val _topShow = MutableLiveData<Show>()
    val topShow: LiveData<Show>
        get() = _topShow

    private val _statusShow = MutableLiveData<RequestStatus>()
    val statusShow: LiveData<RequestStatus>
        get() = _statusShow

    private val _statusEpisode = MutableLiveData<RequestStatus>()
    val statusEpisode: LiveData<RequestStatus>
        get() = _statusEpisode

    private val _show = MutableLiveData<Show>()
    val show: LiveData<Show>
        get() = _show

    private val _episode = MutableLiveData<Episodes>()
    val episodes: LiveData<Episodes>
        get() = _episode

    private var viewModelJob = Job()

    fun searchShows() {
        viewModelScope.launch {
            val callShowsDeferred = repository.getPopularShow()
            val callGenresDeferred = repository.getGenres()
            try {
                _statusPopularShows.postValue(RequestStatus.LOADING)

                val genres = callGenresDeferred.await()
                val shows = callShowsDeferred.await()


                if (shows.results?.size == 0 || genres.genres?.size == 0) {
                    _statusPopularShows.postValue(RequestStatus.EMPTY)
                } else {
                    val showsFiltered =
                        shows.results
                            ?.map { show ->
                                show.copy(genres = genres.genres?.filter {
                                    show.genreIds?.contains(
                                        it.id
                                    ) == true
                                })
                            }
                            ?: emptyList()

                    val topShow = showsFiltered.maxWithOrNull(object : Comparator<Show> {
                        override fun compare(p1: Show, p2: Show): Int = when {
                            p1.popularity!!.toDouble() > p2.popularity!!.toDouble() -> 1
                            p1.popularity.toDouble() == p2.popularity.toDouble() -> 0
                            else -> -1
                        }
                    })

                    _topShow.postValue(topShow)

                    val genreCategoryList: ArrayList<GenreCategory> = arrayListOf()

                    genres.genres?.forEach { genre ->

                        val showsById: ArrayList<Show> = arrayListOf()

                        showsFiltered.forEach {
                            if (it.genreIds?.contains(genre.id)!!) {
                                showsById.add(it)
                            }
                        }

                        if (showsById.size > 0) {
                            genreCategoryList.add(
                                GenreCategory(
                                    id = genre.id!!,
                                    genreName = genre.name!!,
                                    shows = showsById
                                )
                            )
                        }
                    }

                    _popularShows.postValue(genreCategoryList)
                    _statusPopularShows.postValue(RequestStatus.SUCCESS)
                }
            } catch (networkError: Exception) {
                _statusPopularShows.postValue(RequestStatus.ERROR)
            }
        }
    }

    fun searchShowDetailed(tvId: Int) {
        viewModelScope.launch {
            val callDeferred = repository.getShowByID(tvId)
            try {
                _statusShow.postValue(RequestStatus.LOADING)

                var response = callDeferred.await()

                _show.postValue(response)
                _statusShow.postValue(RequestStatus.SUCCESS)
            } catch (networkError: Exception) {
                _statusShow.postValue(RequestStatus.ERROR)
            }
        }
    }

    fun searchEpisode(tvId: Int, seasonNumber: Int) {
        viewModelScope.launch {
            val callDeferred = repository.getEpisodeAsync(tvId, seasonNumber)
            try {
                _statusEpisode.postValue(RequestStatus.LOADING)

                var response = callDeferred.await()
                _episode.postValue(response)
                _statusEpisode.postValue(RequestStatus.SUCCESS)
            } catch (networkError: Exception) {
                _statusEpisode.postValue(RequestStatus.ERROR)
            }
        }
    }

}
