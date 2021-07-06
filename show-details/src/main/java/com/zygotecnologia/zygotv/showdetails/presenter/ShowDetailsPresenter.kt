package com.zygotecnologia.zygotv.showdetails.presenter

import androidx.lifecycle.LiveData
import com.zygotecnologia.zygotv.showdetails.ShowDetails
import com.zygotecnologia.zygotv.showdetails.view.ShowDetailsViewModel

interface ShowDetailsPresenter {
    fun present(showDetails: ShowDetails)
    fun getShowDetailsObserver(): LiveData<ShowDetailsViewModel>
}