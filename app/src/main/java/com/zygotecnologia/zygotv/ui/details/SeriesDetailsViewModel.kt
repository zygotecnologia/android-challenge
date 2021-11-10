package com.zygotecnologia.zygotv.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.data.repository.TmdbRepository
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import kotlinx.coroutines.launch

class SeriesDetailsViewModel(private val repository: TmdbRepository) : ViewModel() {

    private val _detailsShow = MutableLiveData<ShowDetails>()
    val detailsShow: LiveData<ShowDetails>
        get() = _detailsShow

    private val _url = MutableLiveData<String>()
    val posterUrl: LiveData<String>
        get() = _url

    fun loadDetailsShow(genreId: Int) {
        viewModelScope.launch {
            repository.fetchShowAsync(genreId).let { showDetails ->
                _detailsShow.value = showDetails
            }
        }
    }

    fun backDropUrl(show: Show) {
        show.posterPath?.let { poster ->
            val response = ImageUrlBuilder.buildBackdropUrl(poster)
            _url.value = response
        }
    }
}