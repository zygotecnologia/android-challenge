package com.zygotecnologia.zygotv.showdetails.presenter

import androidx.lifecycle.MutableLiveData
import com.zygotecnologia.zygotv.showdetails.ShowDetails
import com.zygotecnologia.zygotv.showdetails.view.EpisodeViewModel
import com.zygotecnologia.zygotv.showdetails.view.SeasonViewModel
import com.zygotecnologia.zygotv.showdetails.view.ShowDetailsViewModel

class ShowDetailsPresenterMock: ShowDetailsPresenter {

    private val showDetailsObserver = MutableLiveData<ShowDetailsViewModel>(
        ShowDetailsViewModel(
            "Community",
            "https://i.pinimg.com/564x/2d/cd/3b/2dcd3be8fd0d527c0629f4f4331c4e7d--community-tv-desktop-wallpapers.jpg",
        )
    )

    override fun getShowDetailsObserver() = showDetailsObserver

    override fun present(showDetails: ShowDetails) {}
}