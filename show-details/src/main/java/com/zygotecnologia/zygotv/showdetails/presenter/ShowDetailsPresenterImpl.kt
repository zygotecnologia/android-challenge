package com.zygotecnologia.zygotv.showdetails.presenter

import androidx.lifecycle.MutableLiveData
import com.zygotecnologia.zygotv.showdetails.view.EpisodeViewModel
import com.zygotecnologia.zygotv.showdetails.view.SeasonViewModel
import com.zygotecnologia.zygotv.showdetails.view.ShowDetailsViewModel

class ShowDetailsPresenterMock {

    private val showDetailsObserver = MutableLiveData<ShowDetailsViewModel>(
        ShowDetailsViewModel(
            "Community",
            "https://i.pinimg.com/564x/2d/cd/3b/2dcd3be8fd0d527c0629f4f4331c4e7d--community-tv-desktop-wallpapers.jpg",
            listOf(
                SeasonViewModel(
                    "Temporada 1",
                    "https://images2.vudu.com/poster2/191155-338",
                    listOf(EpisodeViewModel("Episódio 1", "Abed"))
                )
            )
        )
    )

    fun getShowDetailsObserver() = showDetailsObserver
}