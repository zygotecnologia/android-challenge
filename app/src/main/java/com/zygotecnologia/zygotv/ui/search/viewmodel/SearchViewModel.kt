package com.zygotecnologia.zygotv.ui.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.data.repository.TmdbRepository
import com.zygotecnologia.zygotv.model.Show
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: TmdbRepository) : ViewModel() {

    private val _listPopularShow = MutableLiveData<List<Show>>()
    val listPopularShow: LiveData<List<Show>>
        get() = _listPopularShow

    fun loadPopularShow() {
        val listShow: MutableList<Show> = mutableListOf()
        viewModelScope.launch {
            repository.fetchPopularShowsAsync()?.results?.map { show ->
                listShow.add(show)
            }
            _listPopularShow.value = listShow
        }
    }
}