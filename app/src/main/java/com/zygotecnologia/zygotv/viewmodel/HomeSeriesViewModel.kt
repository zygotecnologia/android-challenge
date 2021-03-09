package com.zygotecnologia.zygotv.viewmodel

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.data.model.ShowResponse
import com.zygotecnologia.zygotv.data.repository.ZygoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeSeriesViewModel(private val repository: ZygoRepository) : ViewModel() {

    val shows : MutableLiveData<ShowResponse> = MutableLiveData()

    init {
        getShows()
    }

    fun getShows() {
        viewModelScope.launch(Dispatchers.Default){
            shows.postValue(repository.getSerie("BR"))
        }
    }

}