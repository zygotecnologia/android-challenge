package com.zygotecnologia.zygotv.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.data.repository.TmdbRepository
import com.zygotecnologia.zygotv.model.InfoGenres
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TmdbRepository) : ViewModel() {

    private val _url = MutableLiveData<String>()
    val posterUrl: LiveData<String>
        get() = _url

    private val _listActionAventure = MutableLiveData<List<Show>>()
    val listActionAventure: LiveData<List<Show>>
        get() = _listActionAventure

    private val _listComedy = MutableLiveData<List<Show>>()
    val listComedy: LiveData<List<Show>>
        get() = _listComedy

    private val _listDrama = MutableLiveData<List<Show>>()
    val listDrama: LiveData<List<Show>>
        get() = _listDrama

    private val _listFamily = MutableLiveData<List<Show>>()
    val listFamily: LiveData<List<Show>>
        get() = _listFamily

    fun loadActionAventure() {
        loadShow(InfoGenres.ACTION_AVENTURE.id, _listActionAventure)
    }

    fun loadComedy() {
        loadShow(InfoGenres.COMEDY.id, _listComedy)
    }

    fun loadDrama() {
        loadShow(InfoGenres.DRAMA.id, _listDrama)
    }

    fun loadFamily() {
        loadShow(InfoGenres.FAMILY.id, _listFamily)
    }

    fun getUrlImage() {
        viewModelScope.launch {
            val posterPath = repository.fetchPopularShowsAsync()?.results?.get(0)?.posterPath
            posterPath?.let { poster ->
                val response = ImageUrlBuilder.buildBackdropUrl(poster)
                _url.value = response
            }
        }
    }

    private fun loadShow(genreId: Int, mutableLiveData: MutableLiveData<List<Show>>) {
        val listGenre: MutableList<Show> = mutableListOf()
        viewModelScope.launch {
            repository.fetchPopularShowsAsync()?.results?.map { showMap: Show ->
                if (showMap.genreIds?.contains(genreId)!!) {
                    listGenre.add(showMap)
                }
            }
            mutableLiveData.value = listGenre
        }
    }
}