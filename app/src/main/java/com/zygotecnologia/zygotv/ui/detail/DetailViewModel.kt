package com.zygotecnologia.zygotv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.ShowsRepository
import com.zygotecnologia.zygotv.utils.SingleLiveEvent

class DetailViewModel(
    private val showsRepository: ShowsRepository
) : ViewModel() {

    private val _show = SingleLiveEvent<Show>()
    val show : LiveData<Show> = _show

    private val _screenStatus = SingleLiveEvent<ScreenStatus>()
    val screenStatus: LiveData<ScreenStatus> = _screenStatus

    suspend fun loadShow(showId: Int) {
        try {
            _screenStatus.value = getScreenStatus()
                .copy(isLoading = true, isError = false)

            _show.value = showsRepository.fetchShow(showId)?.apply {
                fillSeasons()
            }
        } catch (e: Exception) {
            _screenStatus.value = getScreenStatus().copy(isError = true)
        } finally {
            _screenStatus.value = getScreenStatus().copy(isLoading = false)
        }
    }

    private suspend fun Show.fillSeasons() {
        if(id == null || numberOfSeasons == null) return

        val seasonsList = mutableListOf<Season?>()
        (1 .. numberOfSeasons).map { s ->
            seasonsList.add(loadSeason(id, s))
        }
        seasons = seasonsList.filterNotNull()
    }

    private suspend fun loadSeason(showId: Int, seasonNumber: Int): Season? {
        return showsRepository.fetchSeason(showId, seasonNumber)
    }

    private fun getScreenStatus() = screenStatus.value ?: ScreenStatus()

    data class ScreenStatus(
        val isLoading: Boolean = false,
        var isError: Boolean = false
    )
}