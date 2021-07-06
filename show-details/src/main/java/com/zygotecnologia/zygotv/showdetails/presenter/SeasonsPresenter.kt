package com.zygotecnologia.zygotv.showdetails.presenter

import androidx.lifecycle.LiveData
import com.zygotecnologia.zygotv.showdetails.Season
import com.zygotecnologia.zygotv.showdetails.view.SeasonViewModel

interface SeasonsPresenter {
    fun present(seasons: List<Season>)
    fun getSeasonsObserver(): LiveData<List<SeasonViewModel>>
}