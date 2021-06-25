package com.zygotecnologia.zygotv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.ShowsRepository

class DetailViewModel(
    private val showsRepository: ShowsRepository
) : ViewModel() {

    private val _show = MutableLiveData<Show>()
    val show : LiveData<Show> = _show

    private val _loading = MutableLiveData(false)
    val loading : LiveData<Boolean> = _loading

    private val _error = MutableLiveData(false)
    val error : LiveData<Boolean> = _error

    suspend fun loadShow(showId: Int) {
        try {
            _loading.value = true
            val show = showsRepository.fetchShow(showId)
            show?.apply {
                this.numberOfSeasons?.let {
                    val seasons: ArrayList<Season> = ArrayList()
                    for (s in 1..it) {
                        loadSeason(showId, s)?.let { season ->
                            seasons.add(season)
                        }
                    }
                    this.seasons = seasons
                }

                _loading.value = false
                _show.value = this
            }
        } catch (e: Exception) {
            _loading.value = false
            _error.value = true
        }
    }

    private suspend fun loadSeason(showId: Int, seasonNumber: Int): Season? {
        return showsRepository.fetchSeason(showId, seasonNumber)
    }
}