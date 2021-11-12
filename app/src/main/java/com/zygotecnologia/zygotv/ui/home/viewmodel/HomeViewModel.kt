package com.zygotecnologia.zygotv.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.data.repository.TmdbRepository
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TmdbRepository) : ViewModel() {

    private val _url = MutableLiveData<String>()
    val posterUrl: LiveData<String>
        get() = _url

    private val _listShow = MutableLiveData<List<Pair<String, List<Show>>>>()
    val listShow: LiveData<List<Pair<String, List<Show>>>>
        get() = _listShow

    fun getUrlImage() {
        viewModelScope.launch {
            val posterPath = repository.fetchPopularShowsAsync()?.results?.get(0)?.posterPath
            posterPath?.let { poster ->
                val response = ImageUrlBuilder.buildBackdropUrl(poster)
                _url.value = response
            }
        }
    }

    fun loadFullShow() {
        listGenre(
            success = { listGenreShow ->
                viewModelScope.launch {
                    val listCategories = mutableListOf<Pair<String, List<Show>>>()
                    listGenreShow!!.forEach { genre ->
                        val listFilterGenre = mutableListOf<Show>()
                        val genreId = genre.id

                        listFilterGenre.clear()

                        repository.fetchPopularShowsAsync()?.results?.map { show ->
                            if (show.genreIds?.contains(genreId!!)!!) {
                                listFilterGenre.add(show)
                            }
                        }
                        if (listFilterGenre.isNotEmpty()) {
                            listCategories.add(Pair(genre.name!!, listFilterGenre))
                        }
                    }
                    _listShow.value = listCategories
                }
            }
        )
    }

    private fun listGenre(success: (List<Genre>?) -> Unit) {
        viewModelScope.launch {
            repository.fetchGenreAsync()?.genres.let { listGenre ->
                success(listGenre)
            }
        }
    }
}