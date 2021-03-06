package com.zygotecnologia.zygotv.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.api.repository.ApiRepository
import com.zygotecnologia.zygotv.network.retrofit.model.RequestError
import kotlinx.coroutines.launch

class MainViewModel(private val api: ApiRepository) : ViewModel() {

    val errorDialog = MutableLiveData<RequestError>()
    val showList = MutableLiveData<List<Show>>()
    val genreList = MutableLiveData<List<Genre>>()

    fun loadShows() {
        viewModelScope.launch {
            val genresResponse = api.fetchGenres()
            genresResponse.data?.let {
                genreList.postValue(it.genres!!)
            } ?: errorDialog.postValue(genresResponse.error!!)


            val showsResponse = api.fetchPopularShows()
            showList.postValue(showsResponse.data?.results?.map { show ->
                show.copy(genres = genreList.value?.filter { show.genreIds?.contains(it.id) == true }
                )
            })

        }

    }

}
