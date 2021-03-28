package com.zygotecnologia.zygotv.main

import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tmdbApi: TmdbApi
) : ViewModel(), LifecycleObserver {

    private val showList: MutableLiveData<List<Show>> by lazy { MutableLiveData<List<Show>>() }

    private val viewModelJob = SupervisorJob()

    fun loadShows(): LiveData<List<Show>> {
        if (showList.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                fetchShows()
            }
        }
        return showList
    }

    private suspend fun fetchShows() {
        val genres =
                tmdbApi
                        .fetchGenresAsync()
                        ?.genres
                        ?: emptyList()
        val shows =
                tmdbApi
                        .fetchPopularShowsAsync()
                        ?.results
                        ?.map { show ->
                            show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
                        }
                        ?: emptyList()

        withContext(Dispatchers.Main) {
            showList.value = shows
        }
    }

    @MainThread
    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}