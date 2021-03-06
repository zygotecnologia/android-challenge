package com.zygotecnologia.zygotv.main.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.api.repository.ApiRepository
import com.zygotecnologia.zygotv.network.retrofit.model.RequestError
import com.zygotecnologia.zygotv.network.retrofit.model.RetrofitResponse
import kotlinx.coroutines.launch

class MainViewModel(private val api: ApiRepository) : ViewModel() {

    val errorDialog = MutableLiveData<RequestError>()
    val showList = MutableLiveData<List<Show>>()
    val genreList = MutableLiveData<List<Genre>>()

    val sortedShowList : List<Show>? by lazy {
        showList.value?.toList()?.sortedByDescending { it.voteCount }
    }

    fun loadShows() {
        viewModelScope.launch {

            val genresResponse = api.fetchGenres()
            genresResponse.data?.let {
                genreList.postValue(it.genres ?: emptyList())
            } ?: errorDialog.postValue(genresResponse.error ?: RetrofitResponse.genericError)


            val showsResponse = api.fetchPopularShows()
            showList.postValue(showsResponse.data?.results?.map { show ->
                show.copy(genres = genreList.value?.filter { show.genreIds?.contains(it.id) == true }
                )
            })

        }

    }

    fun getMostPopularShow() = sortedShowList?.first()

}
