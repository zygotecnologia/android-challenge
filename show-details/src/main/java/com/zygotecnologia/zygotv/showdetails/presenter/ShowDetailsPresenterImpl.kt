package com.zygotecnologia.zygotv.showdetails.presenter

import androidx.lifecycle.MutableLiveData
import com.zygotecnologia.zygotv.showdetails.ShowDetails
import com.zygotecnologia.zygotv.showdetails.view.EpisodeViewModel
import com.zygotecnologia.zygotv.showdetails.view.SeasonViewModel
import com.zygotecnologia.zygotv.showdetails.view.ShowDetailsViewModel
import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDatabaseImageUrlBuilder

class ShowDetailsPresenterImpl(private val imageUrlBuilder: TheMovieDatabaseImageUrlBuilder) :
    ShowDetailsPresenter {

    private val showDetailsObserver = MutableLiveData<ShowDetailsViewModel>()

    override fun getShowDetailsObserver() = showDetailsObserver

    override fun present(showDetails: ShowDetails) {
        showDetailsObserver.postValue(
            ShowDetailsViewModel(
                showDetails.name,
                imageUrlBuilder.getBackdropCompleteUrl(showDetails.backdropImageUrl)
            )
        )
    }
}