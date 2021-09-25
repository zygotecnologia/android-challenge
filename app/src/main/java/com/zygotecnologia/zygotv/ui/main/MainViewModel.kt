package com.zygotecnologia.zygotv.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.data.model.GenreResponse
import com.zygotecnologia.zygotv.data.model.ShowResponse
import com.zygotecnologia.zygotv.data.repository.TmdbRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {

    private val repository = TmdbRepository()

    private var _genres = MutableLiveData<GenreResponse?>()
    val genres : LiveData<GenreResponse?>
        get() = _genres

    private var _shows = MutableLiveData<ShowResponse?>()
    val shows : LiveData<ShowResponse?>
        get() = _shows

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

    fun getShows() = viewModelScope.launch {
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
}