package com.zygotecnologia.zygotv.viewmodel.description

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zygotecnologia.zygotv.network.TmdbApi
import kotlin.coroutines.CoroutineContext

class DescriptionViewModelFactory(
    private val tmdbApi: TmdbApi,
    private val coroutineContext: CoroutineContext
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DescriptionViewModel(tmdbApi, coroutineContext) as T
    }
}