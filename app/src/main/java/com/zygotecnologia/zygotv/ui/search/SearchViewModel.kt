package com.zygotecnologia.zygotv.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbApi
import com.zygotecnologia.zygotv.network.TmdbClient
import kotlinx.coroutines.launch
import java.net.URLEncoder

class SearchViewModel: ViewModel() {

    private val _results = MutableLiveData<List<Show>?>()
    val results : LiveData<List<Show>?> = _results

    // TODO injection
    private val tmdbApi = TmdbClient.getInstance()

    fun searchTvShow(searchQuery: String) {
        val urlEncodedSearchQuery = URLEncoder.encode(searchQuery, "UTF-8")
        viewModelScope.launch {
            val searchResult = tmdbApi.fetchShowSearch(TmdbApi.TMDB_API_KEY, urlEncodedSearchQuery)
            _results.value = searchResult?.results
        }
    }
}