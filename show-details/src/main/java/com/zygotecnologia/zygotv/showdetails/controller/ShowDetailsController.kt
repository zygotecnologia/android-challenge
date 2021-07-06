package com.zygotecnologia.zygotv.showdetails.controller

import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.core.onBackgroundSync
import com.zygotecnologia.zygotv.showdetails.presenter.SeasonsPresenter
import com.zygotecnologia.zygotv.showdetails.presenter.ShowDetailsPresenter
import com.zygotecnologia.zygotv.showdetails.repository.ShowDetailsRepository
import com.zygotecnologia.zygotv.showdetails.repository.ShowSeasonRepository
import java.io.IOException

class ShowDetailsController(
    private val showDetailsRepository: ShowDetailsRepository,
    private val showSeasonRepository: ShowSeasonRepository,
    private val showDetailsPresenter: ShowDetailsPresenter,
    private val seasonsPresenter: SeasonsPresenter
) : ViewModel() {

    fun fetchItems(id: Int) = onBackgroundSync {
        try {
            showDetailsRepository.get(id).let { showDetails ->
                showDetailsPresenter.present(showDetails)
                seasonsPresenter.present(showDetails.seasonsNumber.map {
                    showSeasonRepository.get(id, it)
                })
            }
        } catch (e: IOException) {
            //TODO tratamento de erro
        }
    }

    class Builder {
        private lateinit var showDetailsPresenter: ShowDetailsPresenter
        private lateinit var seasonsPresenter: SeasonsPresenter

        fun setShowDetailsPresenter(showDetailsPresenter: ShowDetailsPresenter) = apply {
            this.showDetailsPresenter = showDetailsPresenter
        }

        fun setShowSeasonsPresenter(seasonsPresenter: SeasonsPresenter) = apply {
            this.seasonsPresenter = seasonsPresenter
        }

        fun build() = ShowDetailsController(
            ShowDetailsRepository.make(),
            ShowSeasonRepository.make(),
            showDetailsPresenter,
            seasonsPresenter
        )
    }
}