package com.zygotecnologia.zygotv.details.presentation

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.dataOrNull
import com.zygotecnologia.zygotv.tmdb.domain.*
import com.zygotecnologia.zygotv.tmdb.presentation.seasons.ShowDetailItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val showId: Int,
    private val tmdbRepository: TmdbRepository
) : ViewModel() {

    private val _show = MutableStateFlow<ShowWithSeasons?>(null)
    val show: LiveData<Show> = _show
        .filterNotNull()
        .map { it.show }
        .asLiveData()

    private val _showNetworkError = MutableStateFlow(false)
    val showNetworkError: LiveData<Boolean> = _showNetworkError.asLiveData()

    private val selectedSeason = MutableStateFlow<Season?>(null)

    init {
        loadShowData()
    }

    private fun loadShowData() = viewModelScope.launch {
        val show = tmdbRepository.getShow(showId).dataOrNull()

        if (show != null) _show.value = show

        _showNetworkError.value = show == null
    }

    val showDetails: LiveData<List<ShowDetailItem>> = _show
        .filterNotNull()
        .combine(selectedSeason) { show, selectedSeason ->
            show.seasons.toDetailItems(selectedSeason)
        }
        .asLiveData()

    private fun List<SeasonWithEpisodes>.toDetailItems(
        selectedSeason: Season?
    ): List<ShowDetailItem> = flatMap { seasonWithEpisodes ->
        val season = seasonWithEpisodes.season
        val seasonItem = ShowDetailItem.SeasonItem(season)

        val episodeItems = if (season != selectedSeason) emptyList()
        else seasonWithEpisodes.episodes.map { ShowDetailItem.EpisodeItem(it) }

        listOf(seasonItem) + episodeItems
    }

    fun selectSeason(season: Season) {
        val alreadySelectedSeason = selectedSeason.value
        selectedSeason.value = if (season == alreadySelectedSeason) null else season
    }
}
