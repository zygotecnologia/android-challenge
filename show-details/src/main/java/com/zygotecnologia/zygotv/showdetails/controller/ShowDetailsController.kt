package com.zygotecnologia.zygotv.showdetails.controller

import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.core.onBackgroundSync
import com.zygotecnologia.zygotv.showdetails.repository.ShowDetailsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ShowDetailsController(showDetailsRepository: ShowDetailsRepository): ViewModel() {

    fun fetchItems() = onBackgroundSync {

    }
}