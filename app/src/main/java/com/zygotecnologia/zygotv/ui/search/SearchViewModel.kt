package com.zygotecnologia.zygotv.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.ShowsRepository
import kotlinx.coroutines.launch
import java.net.URLEncoder

class SearchViewModel(
    private val showsRepository: ShowsRepository
): ViewModel() {

    private val _results = MutableLiveData<List<Show>?>()
    val results : LiveData<List<Show>?> = _results

    fun searchTvShow(searchQuery: String) {
        val urlEncodedSearchQuery = URLEncoder.encode(searchQuery, "UTF-8")
        viewModelScope.launch {
            val searchResult = showsRepository.fetchShowSearch(urlEncodedSearchQuery)
            _results.value = searchResult?.results
        }
    }
}