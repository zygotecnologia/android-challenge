package com.zygotecnologia.zygotv.details.presentation

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.dataOrNull
import com.zygotecnologia.zygotv.tmdb.domain.*
import com.zygotecnologia.zygotv.tmdb.presentation.seasons.ShowDetailItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val showId: Int,
    private val tmdbRepository: TmdbRepository
) : ViewModel() {

    private val _show = MutableStateFlow<ShowWithSeasons?>(null)

    private val _showNetworkError = MutableStateFlow(false)
    val showNetworkError: LiveData<Boolean> = _showNetworkError.asLiveData()

    init {
        loadShowData()
    }

    private fun loadShowData() = viewModelScope.launch {
        val show = tmdbRepository.getShow(showId).dataOrNull()

        if (show != null) _show.value = show

        _showNetworkError.value = show == null
    }

    private val showWithSeasons: LiveData<ShowWithSeasons> = _show
        .filterNotNull()
        .asLiveData()

    val show: LiveData<Show> = showWithSeasons.map { it.show }

    val showDetails: LiveData<List<ShowDetailItem>> = _show
        .filterNotNull()
        .map { it.seasons.toDetailItems() }
        .asLiveData()

    private fun List<SeasonWithEpisodes>.toDetailItems(): List<ShowDetailItem> = flatMap { seasonWithEpisodes ->
        val seasonItem = ShowDetailItem.SeasonItem(seasonWithEpisodes.season)
        val episodeItems = seasonWithEpisodes.episodes.map { ShowDetailItem.EpisodeItem(it) }

        listOf(seasonItem) + episodeItems
    }
}
