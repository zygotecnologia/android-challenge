package com.zygotecnologia.zygotv.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.network.api.repository.ApiRepository
import com.zygotecnologia.zygotv.network.retrofit.model.RequestError
import com.zygotecnologia.zygotv.network.retrofit.model.RetrofitResponse
import com.zygotecnologia.zygotv.network.retrofit.validateResponse
import kotlinx.coroutines.launch

class DetailsViewModel(private val api: ApiRepository) : ViewModel() {

    val errorDialog = MutableLiveData<RequestError>()
    val showDetails = MutableLiveData<ShowDetails>()

    fun loadShowDetails(showId : Int) {
        viewModelScope.launch {
            fetchDetails(showId)
        }
    }

    private suspend fun fetchDetails(showId : Int) {
        val resource = api.fetchShow(showId)
        resource.validateResponse(showDetails, errorDialog)
    }

}
