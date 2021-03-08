package com.zygotecnologia.zygotv.ui.series.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zygotecnologia.zygotv.data.model.ShowResponse
import com.zygotecnologia.zygotv.data.repository.ZygoRepository

class HomeSeriesViewModel(repository: ZygoRepository) : ViewModel() {

    val shows : LiveData<ShowResponse> = liveData {
        repository.getSerie("BR")?.let { emit(it) }
    }

//    private val genders: LiveData<List<Genre>> = liveData {
//        repository.getGenders("BR")?.genres?.let { emit(it) }
//    }

}