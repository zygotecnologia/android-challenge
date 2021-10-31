package com.zygotecnologia.zygotv.viewmodel.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.network.TmdbApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DescriptionViewModel(
    private val tmdbApi: TmdbApi,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val _viewState = MutableLiveData<DescriptionViewState>()
    val viewState: LiveData<DescriptionViewState> = _viewState

    fun fetchShowDescription(showId: Int) {
        if (showId != 0) {
            CoroutineScope(coroutineContext).launch {
                loadShowDescription(showId)
            }
        } else {
            _viewState.value = DescriptionViewState.Error
        }
    }

    private suspend fun loadShowDescription(showId: Int) {
        val showDescription = tmdbApi.fetchShowAsync(showId, TmdbApi.TMDB_API_KEY)
        _viewState.postValue(showDescription?.let { DescriptionViewState.ShowDescription(it) })
    }
}