package com.zygotecnologia.zygotv.viewmodel.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.network.TmdbApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchViewModel(
    private val tmdb: TmdbApi,
    private val coroutinesContext: CoroutineContext
) : ViewModel() {

    private val _viewState = MutableLiveData<SearchViewState>()
    val viewState: LiveData<SearchViewState> = _viewState

    fun fetchSearchShow(keyword: String) {
        _viewState.value = SearchViewState.Loading
        CoroutineScope(coroutinesContext).launch {
            loadSearchShow(keyword)
        }
    }

    private suspend fun loadSearchShow(keyword: String) {
       tmdb.fetchSearchShow(keyword)?.results?.run {
            _viewState.postValue(SearchViewState.SearchSuccess(this))
        }
    }
}