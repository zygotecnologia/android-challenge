package com.zygotecnologia.zygotv.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.data.model.GenreResponse
import com.zygotecnologia.zygotv.data.model.Season
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.data.model.ShowResponse
import com.zygotecnologia.zygotv.data.repository.TmdbRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel (
    private val repository: TmdbRepository
    ): ViewModel() {

    private var _genres = MutableLiveData<GenreResponse?>()
    val genres : LiveData<GenreResponse?>
        get() = _genres

    private var _shows = MutableLiveData<ShowResponse?>()
    val shows : LiveData<ShowResponse?>
        get() = _shows

    private var _show = MutableLiveData<Show?>()
    val show : LiveData<Show?>
        get() = _show

    private var _season = MutableLiveData<Season?>()
    val season : LiveData<Season?>
        get() = _season

    private var _seasons = MutableLiveData<MutableList<Season>>(mutableListOf())
    val seasons : LiveData<MutableList<Season>>
        get() = _seasons


    fun getGenres() = viewModelScope.launch {
        try {
            val genres = repository.fetchGenresAsync()
            if( genres!= null ){
                _genres.postValue(genres)
            }
        }
        catch (ex: Exception){
            Log.e("Error", "${ex.message}")
        }
    }

    fun getPopularShows() = viewModelScope.launch {
        try {
            val shows = repository.fetchPopularShowsAsync()
            if( shows!= null ){
                _shows.postValue(shows)
            }
        }
        catch (ex: Exception){
            Log.e("Error", "${ex.message}")
        }
    }

    fun getShow(id: Int) = viewModelScope.launch {
        try {
            val show = repository.fetchShowAsync(id)
            _show.postValue(show)
            show?.numberOfSeasons?.let {

                for (numero in 1..it ){
                    getSeason(id, numero)
                }
            }
        }
        catch (ex: Exception){
            Log.e("Error", "${ex.message}")
        }
    }

    fun getSeason(id: Int, seasonNumber: Int) = viewModelScope.launch {
        try {
            val season = repository.fetchSeasonAsync(id, seasonNumber)
            val seasonsList = seasons.value
            if (season != null && seasonsList !== null ) {
                seasonsList.add(season)
                _seasons.postValue(seasonsList)
            }

        }
        catch (ex: Exception){
            Log.e("Error", "${ex.message}")
        }
    }
}