package com.zygotecnologia.zygotv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbApi
import com.zygotecnologia.zygotv.network.TmdbClient

class DetailViewModel : ViewModel() {

    private val _show = MutableLiveData<Show>()
    val show : LiveData<Show> = _show

    // TODO injection
    private val tmdbApi = TmdbClient.getInstance()

    suspend fun loadShow(showId: Int) {
        val show = tmdbApi.fetchShowAsync(showId, TmdbApi.TMDB_API_KEY)
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

            _show.value = this
        }
    }

    private suspend fun loadSeason(showId: Int, seasonNumber: Int): Season? {
        return tmdbApi.fetchSeasonAsync(
           showId, seasonNumber, TmdbApi.TMDB_API_KEY
        )
    }
}