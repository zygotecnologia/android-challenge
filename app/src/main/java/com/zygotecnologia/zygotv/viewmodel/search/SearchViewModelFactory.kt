package com.zygotecnologia.zygotv.viewmodel.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zygotecnologia.zygotv.network.TmdbApi
import kotlin.coroutines.CoroutineContext

class SearchViewModelFactory(
    private val tmdbApi: TmdbApi,
    private val coroutineContext: CoroutineContext
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(tmdbApi, coroutineContext) as T
    }
}