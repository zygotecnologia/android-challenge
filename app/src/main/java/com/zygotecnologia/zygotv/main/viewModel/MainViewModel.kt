package com.zygotecnologia.zygotv.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.genre.Genre
import com.zygotecnologia.zygotv.model.show.Show
import com.zygotecnologia.zygotv.network.api.repository.ApiRepository
import com.zygotecnologia.zygotv.network.model.RequestError
import com.zygotecnologia.zygotv.network.model.RetrofitResponse
import kotlinx.coroutines.launch

class MainViewModel(api: ApiRepository) : BaseViewModel(api) {

    val showList = MutableLiveData<List<Show>>()
    val genreList = MutableLiveData<List<Genre>>()

    private val sortedShowList : List<Show>? by lazy {
        showList.value?.toList()?.sortedByDescending { it.voteCount }
    }

    val filteredGenresList : List<Genre> by lazy {
        val filtered = mutableListOf<Genre>()
        genreList.value?.forEach { genre ->
            val a = showList.value!!.filter { it.genreIds!!.contains(genre.id) }
            if(a.isNotEmpty()) filtered.add(genre)
        }
        filtered as List<Genre>
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
        showList.postValue(showsResponse.data?.results?.map { show ->
            show.copy(genres = genreList.value?.filter { show.genreIds?.contains(it.id) == true }
            )
        })
    }

    private suspend fun fetchGenres() {
        val genresResponse = api.fetchGenres()
        genresResponse.data?.let {
            genreList.postValue(it.genres ?: emptyList())
        } ?: errorDialog.postValue(genresResponse.error ?: RetrofitResponse.genericError)
    }

    fun getMostPopularShow() = sortedShowList?.first()

}
