package com.zygotecnologia.zygotv.showdetails.presenter

import androidx.lifecycle.LiveData
import com.zygotecnologia.zygotv.showdetails.ShowDetails
import com.zygotecnologia.zygotv.showdetails.view.ShowDetailsViewModel
import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDatabaseImageUrlBuilder

interface ShowDetailsPresenter {
    fun present(showDetails: ShowDetails)
    fun getShowDetailsObserver(): LiveData<ShowDetailsViewModel>

    companion object Factory {
        fun make(): ShowDetailsPresenter = ShowDetailsPresenterImpl(TheMovieDatabaseImageUrlBuilder.make())
    }
}