package com.zygotecnologia.zygotv.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.model.Show

class MainViewModel: ViewModel() {

    val shows: LiveData<List<Show>> = MutableLiveData(emptyList())

    private fun loadShows() {

    }
}
