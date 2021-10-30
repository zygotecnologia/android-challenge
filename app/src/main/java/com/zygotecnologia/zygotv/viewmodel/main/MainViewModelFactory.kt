package com.zygotecnologia.zygotv.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zygotecnologia.zygotv.network.TmdbApi
import kotlin.coroutines.CoroutineContext

class MainViewModelFactory(
    private val tmdbApi: TmdbApi,
    private val coroutineContext: CoroutineContext
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(tmdbApi, coroutineContext) as T
    }
}