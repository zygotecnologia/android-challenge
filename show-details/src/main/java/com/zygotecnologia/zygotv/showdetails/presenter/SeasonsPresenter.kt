package com.zygotecnologia.zygotv.showdetails.presenter

import androidx.lifecycle.LiveData
import com.zygotecnologia.zygotv.showdetails.Season
import com.zygotecnologia.zygotv.showdetails.view.SeasonViewModel
import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDatabaseImageUrlBuilder

interface SeasonsPresenter {
    fun present(seasons: List<Season>)
    fun getSeasonsObserver(): LiveData<List<SeasonViewModel>>

    companion object Factory {
        fun make(): SeasonsPresenter = SeasonsPresenterImpl(TheMovieDatabaseImageUrlBuilder.make())
    }
}