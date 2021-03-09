package com.zygotecnologia.zygotv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zygotecnologia.zygotv.data.model.ShowResponse
import com.zygotecnologia.zygotv.data.repository.ZygoRepository

class SearchResultViewModel(repository: ZygoRepository) : ViewModel() {

    val shows : LiveData<ShowResponse> = liveData {
        repository.getSerie("BR")?.let { emit(it) }
    }

    val textFilter : MutableLiveData<String> = MutableLiveData()

}