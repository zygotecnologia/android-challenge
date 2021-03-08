package com.zygotecnologia.zygotv.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.season.SeasonResponse
import com.zygotecnologia.zygotv.model.show.ShowDetails
import com.zygotecnologia.zygotv.network.api.repository.ApiRepository
import com.zygotecnologia.zygotv.network.model.RequestError
import com.zygotecnologia.zygotv.network.retrofit.validateResponse
import kotlinx.coroutines.launch

class DetailsViewModel(api: ApiRepository) : BaseViewModel(api) {

    val showDetails = MutableLiveData<ShowDetails>()
    lateinit var seasons : MutableList<SeasonResponse>

    val eventDataLoaded = MutableLiveData<Boolean>(false)

    fun loadShowDetails(showId : Int) {
        viewModelScope.launch {
            loading(true)
            fetchShowDetails(showId)
        }
    }

    fun loadSeasonsDetails(showId: Int) {
        viewModelScope.launch {
            fetchSeasonsDetails(showId)
        }
    }

    suspend fun fetchSeasonsDetails(showId : Int) {
        seasons = mutableListOf()
        showDetails.value?.seasons?.forEach { season ->
            val resource = api.fetchSeasonDetails(showId, season.seasonNumber!!)
            resource.data?.let {
                seasons.add(it)
            }
        }
        eventDataLoaded.postValue(true)
        loading(false)

    }

    private suspend fun fetchShowDetails(showId : Int) {
        val resource = api.fetchShow(showId)
        resource.validateResponse(showDetails, errorDialog)
    }

}
