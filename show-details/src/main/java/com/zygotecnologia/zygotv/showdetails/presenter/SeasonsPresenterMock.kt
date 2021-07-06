package com.zygotecnologia.zygotv.showdetails.presenter

import androidx.lifecycle.MutableLiveData
import com.zygotecnologia.zygotv.showdetails.Season
import com.zygotecnologia.zygotv.showdetails.view.EpisodeViewModel
import com.zygotecnologia.zygotv.showdetails.view.SeasonViewModel
import com.zygotecnologia.zygotv.showdetails.view.ShowDetailsViewModel

class SeasonsPresenterMock: SeasonsPresenter {

    override fun present(seasons: List<Season>) {}

    override fun getSeasonsObserver() = seasonsObserver

    private val seasonsObserver = MutableLiveData<List<SeasonViewModel>>(
        listOf(
            SeasonViewModel(
                "Temporada 1",
                "Overview da temporada",
                "https://images2.vudu.com/poster2/191155-338",
                listOf(EpisodeViewModel("Episódio 1", "Abed"))
            )
        )
    )
}