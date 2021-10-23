package com.zygotecnologia.zygotv.details.presentation

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.dataOrNull
import com.zygotecnologia.zygotv.tmdb.domain.*
import com.zygotecnologia.zygotv.tmdb.presentation.seasons.ShowDetailItem
import kotlinx.coroutines.flow.flow

class DetailsViewModel(
    private val showId: Int,
    private val tmdbRepository: TmdbRepository
) : ViewModel() {

    private val _showNetworkError = MutableLiveData(false)
    val showNetworkError: LiveData<Boolean> = _showNetworkError

    private val _show = flow {
        val show = tmdbRepository.getShow(showId).dataOrNull()

        if (show != null) emit(show)
        _showNetworkError.postValue(show == null)
    }
    private val showWithSeasons: LiveData<ShowWithSeasons> = _show.asLiveData()

    val show: LiveData<Show> = showWithSeasons.map { it.show }

    val showDetails: LiveData<List<ShowDetailItem>> = showWithSeasons.map { it.seasons.toDetailItems() }

    private fun List<SeasonWithEpisodes>.toDetailItems(): List<ShowDetailItem> = flatMap { seasonWithEpisodes ->
        val seasonItem = ShowDetailItem.SeasonItem(seasonWithEpisodes.season)
        val episodeItems = seasonWithEpisodes.episodes.map { ShowDetailItem.EpisodeItem(it) }

        listOf(seasonItem) + episodeItems
    }
}
