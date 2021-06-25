package com.zygotecnologia.zygotv.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.ShowsRepository
import com.zygotecnologia.zygotv.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import java.net.URLEncoder

class SearchViewModel(
    private val showsRepository: ShowsRepository
): ViewModel() {

    private val _results = SingleLiveEvent<List<Show>?>()
    val results : LiveData<List<Show>?> = _results

    private val _loading = SingleLiveEvent<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _error = SingleLiveEvent<Boolean>()
    val error : LiveData<Boolean> = _error

    fun searchTvShow(searchQuery: String) {
        val urlEncodedSearchQuery = URLEncoder.encode(searchQuery, "UTF-8")
        viewModelScope.launch {
            try {
                _loading.value = true

                val searchResult = showsRepository.fetchShowSearch(urlEncodedSearchQuery)

                _loading.value = false
                _results.value = searchResult?.results

            } catch (e: Exception) {
                _loading.value = false
                _error.value = true
            }
        }
    }
}