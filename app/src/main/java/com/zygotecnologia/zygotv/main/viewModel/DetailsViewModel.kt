package com.zygotecnologia.zygotv.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.season.SeasonResponse
import com.zygotecnologia.zygotv.model.show.ShowDetails
import com.zygotecnologia.zygotv.network.api.repository.ApiRepository
import com.zygotecnologia.zygotv.network.model.RequestError
import com.zygotecnologia.zygotv.network.model.Resource
import com.zygotecnologia.zygotv.network.retrofit.validateResponse
import kotlinx.coroutines.launch

class DetailsViewModel(api: ApiRepository) : BaseViewModel(api) {

    lateinit var seasons: MutableList<SeasonResponse>

    val showDetails = MutableLiveData<ShowDetails>()
    val eventDataLoaded = MutableLiveData<Boolean>(false)

    fun loadShowDetails(showId: Int) {
        viewModelScope.launch {
            loading(true)
            fetchShowDetails(showId)
        }
    }

    fun loadSeasonsDetails(showId: Int) {
        viewModelScope.launch {
            fetchSeasonsDetails(showId)
            loading(false)
        }
    }

    suspend fun fetchSeasonsDetails(showId: Int) {
        seasons = mutableListOf()
        showDetails.value?.seasons?.forEach { season ->
            season.seasonNumber?.let { seasonNumber ->
                val resource = api.fetchSeasonDetails(showId, seasonNumber)
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        resource.data?.let {
                            seasons.add(it)
                        }
                    }
                    else -> resource.error?.let {
                        errorDialog.postValue(it)
                        loading(false)
                    }
                }
                eventDataLoaded.postValue(true)
            }
        }

    }

    private suspend fun fetchShowDetails(showId: Int) {
        val resource = api.fetchShow(showId)
        resource.validateResponse(showDetails, errorDialog)
    }

}
