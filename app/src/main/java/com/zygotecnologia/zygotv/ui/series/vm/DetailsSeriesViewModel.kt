package com.zygotecnologia.zygotv.ui.series.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zygotecnologia.zygotv.data.repository.ZygoRepository
import com.zygotecnologia.zygotv.data.model.Season
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.data.model.seasondetail.SeasonDetail

class DetailsSeriesViewModel(private val repository: ZygoRepository) : ViewModel() {
    var tvId : Int = 0
        get() = field
        set(value) {field = value }

    var seasonNumber : Int = 0
        get() = field
        set(value) {field = value }


        val full : LiveData<List<Season>> = liveData {
            val show = repository.getSeasonsDetails(tvId)
            var listSeasonDetail:List<Season> = mutableListOf()
            show?.seasons?.forEach{
                seasonNumber = it.seasonNumber
                it.seasonDetail = repository.getSeasonsEpisodesDetails(tvId,seasonNumber)!!
                listSeasonDetail += it

            }
            emit(listSeasonDetail)
        }

    val season : LiveData<Show?> = liveData {
        emit(repository.getSeasonsDetails(tvId))

    }

    val episodes : LiveData<SeasonDetail?> = liveData {
        emit(repository.getSeasonsEpisodesDetails(tvId, seasonNumber))
    }


}