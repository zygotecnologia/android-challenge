package com.zygotecnologia.zygotv.views.main

import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.entity.Section
import com.zygotecnologia.zygotv.model.entity.SectionType
import com.zygotecnologia.zygotv.model.entity.Show
import com.zygotecnologia.zygotv.model.network.ApiCaller
import com.zygotecnologia.zygotv.model.network.NetworkResult
import com.zygotecnologia.zygotv.model.network.services.TmdbApi
import com.zygotecnologia.zygotv.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

/* MainViewModel
 * Load data from API and order the shows in sections
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tmdbApi: TmdbApi
) : ViewModel(), LifecycleObserver {

    private val sectionList: MutableLiveData<List<Section>> by lazy { MutableLiveData<List<Section>>() }
    private val _screenState: MutableLiveData<ScreenState> by lazy { MutableLiveData<ScreenState>() }

    val screenState: LiveData<ScreenState>
        get() = _screenState

    //Return section list from memory or call the API
    fun loadSections(): LiveData<List<Section>> {
        if (sectionList.value == null) {
            _screenState.value = ScreenState.LOADING

            viewModelScope.launch {
                fetchData()
            }
        }
        return sectionList
    }

    //Fetch shows and genres from API
    private suspend fun fetchData() {
        val genresResult = ApiCaller().safeApiCall(Dispatchers.IO) {
            tmdbApi.fetchGenresAsync()
        }

        when (genresResult) {
            is NetworkResult.NetworkError -> {
                withContext(Dispatchers.Main) {
                    _screenState.value = ScreenState.NETWORK_ERROR
                }
            }
            is NetworkResult.GenericError -> {
                withContext(Dispatchers.Main) {
                    _screenState.value = ScreenState.GENERIC_ERROR
                }
            }
            is NetworkResult.Success -> {
                val genres = genresResult.value
                        ?.genres
                        ?: emptyList()

                fetchShows(genres)
            }
        }
    }

    private suspend fun fetchShows(genres: List<Genre>) {
        val showsResult = ApiCaller().safeApiCall(Dispatchers.IO) {
            tmdbApi.fetchPopularShowsAsync()
        }

        when (showsResult) {
            is NetworkResult.NetworkError -> {
                withContext(Dispatchers.Main) {
                    _screenState.value = ScreenState.NETWORK_ERROR
                }
            }
            is NetworkResult.GenericError -> {
                withContext(Dispatchers.Main) {
                    _screenState.value = ScreenState.GENERIC_ERROR
                }
            }
            is NetworkResult.Success -> {
                val shows = showsResult.value
                        ?.results
                        ?.map { show ->
                            show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
                        }
                        ?: emptyList()

                buildSectionList(genres, shows.toMutableList())
            }
        }
    }

    //Turn shows and genres in a section list
    private suspend fun buildSectionList(genres: List<Genre>, shows: MutableList<Show>) {
        val sections = mutableListOf<Section>()

        //Extract the first to be a highlight
        val highlight = shows.first()
        sections.add(Section(highlight.name ?: "", SectionType.HIGHLIGHT, listOf(highlight)))
        shows.removeAt(0)

        //Sort other shows by genre
        val sortedShows: List<Section> = genres
            .map { genre ->
                val showList = shows.filter { it.genres?.contains(genre) ?: false }
                Section(genre.name ?: "", SectionType.LIST, showList)
            }
            //Filter genres without shows
            .filter { it.shows.isNotEmpty() }

        sections.addAll(sortedShows)

        withContext(Dispatchers.Main) {
            _screenState.value = ScreenState.SUCCESS
            sectionList.value = sections
        }
    }

    @MainThread
    override fun onCleared() {
        super.onCleared()
    }
}