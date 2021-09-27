package com.zygotecnologia.zygotv.series.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.GenreSerie
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.SeriesRepository
import kotlinx.coroutines.launch

class SeriesViewModel(private val seriesRepository: SeriesRepository) : ViewModel() {
    private val mutableListGenres: MutableLiveData<List<GenreSerie>> = MutableLiveData()
    val dataListGenres: LiveData<List<GenreSerie>> = mutableListGenres
    private val mutablePopularSerie: MutableLiveData<List<Show>> = MutableLiveData()
    val dataListPopular: LiveData<List<Show>> = mutablePopularSerie
    private val mutableSerieDetails: MutableLiveData<Show> = MutableLiveData()
    val dataSeriesDetails: LiveData<Show> = mutableSerieDetails
    private val mutableSeasonList: MutableLiveData<List<Season>> = MutableLiveData()
    val dataSeasonList: LiveData<List<Season>> = mutableSeasonList
    private val mutableSearch: MutableLiveData<List<Show>> = MutableLiveData()
    val dataSearch: LiveData<List<Show>> = mutableSearch
    private val mutableLoading: MutableLiveData<Boolean> = MutableLiveData()
    val liveLoading: LiveData<Boolean> = mutableLoading

    fun loadShows() {
        var series: List<Show> = emptyList()
        var genres: List<Genre> = arrayListOf()
        viewModelScope.launch {
            seriesRepository.getGenres().let {
                if (it.isSuccessful) {
                    genres = it.body()?.genres!!
                }
            }
            seriesRepository.getShows().let {
                if (it.isSuccessful) {
                    series = it.body()?.results!!
                    mutablePopularSerie.value = series
                }
            }
            if (!series.isNullOrEmpty())
                mapGenreSeries(series, genres)
        }
    }

    private fun mapGenreSeries(series: List<Show>, genres: List<Genre>) {
        val listGenreSerie = mutableListOf<GenreSerie>()
        genres.map { genre ->
            genre.id.let {
                var fisteredList: MutableList<Show> = mutableListOf()
                series.map { serie ->
                    if (serie.genreIds!!.contains(genre.id))
                        fisteredList.add(serie)
                }
                if (!fisteredList.isNullOrEmpty())
                    listGenreSerie.add(GenreSerie(fisteredList, genre.name))
            }
        }
        mutableLoading.value = false
        mutableListGenres.value = listGenreSerie
    }

    fun getSerieDetails(id: Int) {
        viewModelScope.launch {
            seriesRepository.getShowDetails(id).let {
                if (it.isSuccessful) {
                    mutableSerieDetails.value = it.body()
                }
            }
        }
    }

    fun getSearch(query: String) {
        viewModelScope.launch {
            seriesRepository.getSearchSeries(query).let {
                if (it.isSuccessful) {
                    mutableSearch.value = it.body()?.results
                }
            }
        }
    }

    fun loadSeasonDetails(serieDetails: Show) {
        val listSeason: MutableList<Season> = mutableListOf()
        viewModelScope.launch {
            serieDetails.seasons?.forEach { season ->
                if (serieDetails.id != null && season.seasonNumber != null) {
                    seriesRepository.getSeasonDetails(
                        serieDetails.id,
                        season.seasonNumber
                    ).let {
                        if (it.isSuccessful && it.body() != null) {
                            listSeason.add(it.body()!!)
                        }
                    }
                }
            }
            mutableLoading.value = false
            mutableSeasonList.value = listSeason
        }
    }


}