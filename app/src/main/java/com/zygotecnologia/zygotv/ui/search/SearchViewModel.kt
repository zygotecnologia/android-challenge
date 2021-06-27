package com.zygotecnologia.zygotv.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.data.repository.ShowsRepositoryImpl
import com.zygotecnologia.zygotv.repository.ShowsRepository
import com.zygotecnologia.zygotv.utils.SingleLiveEvent
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

    suspend fun searchTvShow(searchQuery: String) {
        try {
            showLoading()
            val urlEncodedSearchQuery = URLEncoder.encode(searchQuery, "UTF-8")
            val searchResult = showsRepository.fetchShowSearch(urlEncodedSearchQuery)
            sendResults(searchResult?.results)
        } catch (e: Exception) {
            showError()
        } finally {
            hideLoading()
        }
    }

    private fun hideLoading() {
        _loading.value = false
    }

    private fun showLoading() {
        _loading.value = true
    }

    private fun showError() {
        _error.value = true
    }

    private fun sendResults(shows: List<Show>?) {
        _results.value = shows
    }
}