package com.zygotecnologia.zygotv.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.genre.Genre
import com.zygotecnologia.zygotv.model.show.Show
import com.zygotecnologia.zygotv.network.api.repository.ApiRepository
import com.zygotecnologia.zygotv.network.model.RequestError
import com.zygotecnologia.zygotv.network.model.Resource
import com.zygotecnologia.zygotv.network.model.RetrofitResponse
import kotlinx.coroutines.launch

class MainViewModel(api: ApiRepository) : BaseViewModel(api) {

    val showList = MutableLiveData<List<Show>>()
    val genreList = MutableLiveData<List<Genre>>()
    val input = MutableLiveData("")

    private val sortedShowList: List<Show>? by lazy {
        showList.value?.toList()?.sortedByDescending { it.voteCount }
    }

    val showNames : List<String> by lazy {
        val names = mutableListOf<String>()
        showList.value?.forEach {  show ->
            show.name?.let {
                names.add(it)
            }
        }
        names
    }

    val filteredGenresList: List<Genre> by lazy {
        val filtered = mutableListOf<Genre>()
        genreList.value?.forEach { genre ->
            val show = showList.value?.filter { it.genreIds?.contains(genre.id) ?: false }
            if (show?.isNotEmpty() == true) filtered.add(genre)
        }
        filtered
    }

    fun loadShows() {
        viewModelScope.launch {
            loading(true)
            fetchGenres()
            fetchShows()
            loading(false)
        }
    }

    private suspend fun fetchShows() {
        val showsResponse = api.fetchPopularShows()
        when (showsResponse.status) {
            Resource.Status.SUCCESS -> {
                showList.postValue(
                    showsResponse.data?.results?.map { show ->
                        show.copy(genreList.value?.filter { show.genreIds?.contains(it.id) == true } )
                    }
                )
            }
            else -> showsResponse.error?.let {
                errorDialog.postValue(it)
                loading(false)
            }
        }
    }

    private suspend fun fetchGenres() {
        val genresResponse = api.fetchGenres()
        when (genresResponse.status) {
            Resource.Status.SUCCESS -> {
                genresResponse.data?.let {
                    genreList.postValue(it.genres ?: emptyList())
                }
            }
            else -> genresResponse.error?.let {
                errorDialog.postValue(it)
                loading(false)
            }
        }
    }

    fun getMostPopularShow() = sortedShowList?.first()

    fun findShowByName(showName : String): Show? {
        return showList.value?.find { it.name == showName }
    }

}
