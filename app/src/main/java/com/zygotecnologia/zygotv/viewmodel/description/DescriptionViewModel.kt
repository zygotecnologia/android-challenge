package com.zygotecnologia.zygotv.viewmodel.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.model.Show
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
            _viewState.value = DescriptionViewState.Loading
            CoroutineScope(coroutineContext).launch {
                loadShowDescription(showId)
            }
        } else {
            _viewState.value = DescriptionViewState.Error
        }
    }

    private suspend fun loadShowDescription(showId: Int) {
        tmdbApi.fetchShowAsync(showId)?.let {
            loadSeasonEpisodes(it)
        }
    }

    private suspend fun loadSeasonEpisodes(showDescription: Show) {
        showDescription.season?.forEachIndexed { index, _ ->
            var indexSearch = index
            if (showDescription.season.size <= 1) {
                indexSearch++
            }

            showDescription.season[index].episodes?.addAll(
                showDescription.id?.let {
                    tmdbApi.fetchSeasonEpisodes(it, indexSearch)?.episodes
                } as List
            )
        }

        _viewState.postValue(DescriptionViewState.ShowAndSeasonsDescriptions(showDescription))
    }
}