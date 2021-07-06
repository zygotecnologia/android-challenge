package com.zygotecnologia.zygotv.showdetails.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zygotecnologia.zygotv.showdetails.Season
import com.zygotecnologia.zygotv.showdetails.view.EpisodeViewModel
import com.zygotecnologia.zygotv.showdetails.view.SeasonViewModel
import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDatabaseImageUrlBuilder

class SeasonsPresenterImpl(private val imageUrlBuilder: TheMovieDatabaseImageUrlBuilder) :
    SeasonsPresenter {

    private val observer = MutableLiveData<List<SeasonViewModel>>()

    override fun getSeasonsObserver() = observer

    override fun present(seasons: List<Season>) {
        observer.postValue(seasons.map { season ->
            SeasonViewModel(
                "Temporada ${season.number}",
                season.overview,
                imageUrlBuilder.getPosterCompleteUrl(season.posterImageUrl),
                season.episodes.map { EpisodeViewModel(it.name, it.description) }
            )
        })
    }

}