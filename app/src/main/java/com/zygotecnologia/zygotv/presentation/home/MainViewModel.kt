package com.zygotecnologia.zygotv.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.uistate.State
import com.zygotecnologia.zygotv.common.launch
import com.zygotecnologia.zygotv.service.remote.data.ShowResponse
import com.zygotecnologia.zygotv.service.remote.repository.MainRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _showLiveData = MutableLiveData<State<List<ShowResponse>>>()

    val showResponseLiveData: LiveData<State<List<ShowResponse>>> = _showLiveData

    fun getHomeMovies() {
        launch {
            repository.getGenres().onStart { _showLiveData.value = State.loading() }
                .map { resource -> State.fromResource(resource) }
                .collect { value -> _showLiveData.value = value }
        }

    }
}