package com.zygotecnologia.zygotv.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.data.model.ShowDetailsModel.ShowDetailsResponse
import com.zygotecnologia.zygotv.data.repository.ShowsRepository
import com.zygotecnologia.zygotv.network.Resource
import kotlinx.coroutines.launch

class ShowDetailsViewModel(
    private val showsRepository: ShowsRepository
) : ViewModel() {
    private val _showDetails: MutableLiveData<Resource<ShowDetailsResponse>> = MutableLiveData()
    val showDetail: LiveData<Resource<ShowDetailsResponse>> = _showDetails

    fun getShowDetails(showId: Int) = viewModelScope.launch {
        _showDetails.value = Resource.Loading
        _showDetails.value = showsRepository.getShowDetails(showId)
    }
}