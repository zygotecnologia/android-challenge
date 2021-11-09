package com.zygotecnologia.zygotv.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.data.repository.ShowsRepository
import com.zygotecnologia.zygotv.network.Resource
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.data.model.GenreResponse
import com.zygotecnologia.zygotv.data.model.ShowResponse
import kotlinx.coroutines.launch


class HomeViewModel(
    private val showsRepository: ShowsRepository
) : ViewModel() {

    private val _genres: MutableLiveData<Resource<GenreResponse>> = MutableLiveData()
    val genres: LiveData<Resource<GenreResponse>> = _genres

    private  val _shows: MutableLiveData<Resource<ShowResponse>> = MutableLiveData()
    val shows: LiveData<Resource<ShowResponse>> = _shows

    fun getGenres() = viewModelScope.launch{
        _genres.value = Resource.Loading
        _genres.value = showsRepository.getGenres()
    }

    fun getShows() = viewModelScope.launch {
        _shows.value = Resource.Loading
        _shows.value = showsRepository.getShows()
    }
}