package com.zygotecnologia.zygotv.views.details

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.model.entity.Season
import com.zygotecnologia.zygotv.model.entity.Show
import com.zygotecnologia.zygotv.model.network.ApiCaller
import com.zygotecnologia.zygotv.model.network.NetworkResult
import com.zygotecnologia.zygotv.model.network.services.TmdbApi
import com.zygotecnologia.zygotv.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel  @Inject constructor(
    private val tmdbApi: TmdbApi
) : ViewModel(), LifecycleObserver {

    private val showItem: MutableLiveData<Show> by lazy { MutableLiveData<Show>() }
    private val _screenState: MutableLiveData<ScreenState> by lazy { MutableLiveData<ScreenState>() }

    val screenState: LiveData<ScreenState>
        get() = _screenState

    //Return show from memory or call API
    fun loadShowId(showId: Int): LiveData<Show> {
        if (showItem.value == null) {
            _screenState.value = ScreenState.LOADING

           viewModelScope.launch {
                fetchData(showId)
            }
        }
        return showItem
    }

    //Load show data
    private suspend fun fetchData(showId: Int) {
        val showResult = ApiCaller().safeApiCall(Dispatchers.IO) {
            tmdbApi.fetchShowAsync(showId)
        }

        //Verify if some error occur
        if (showResult is NetworkResult.Success) {
            val show = showResult.value
            var error = false

            val seasons = mutableListOf<Season>()

            //With the show data, load season data one by one
            show?.seasons?.forEach { season ->
                val seasonData = fetchSeason(showId, season.seasonNumber ?: -1)

                //If some error occur on fetch season data break the loop
                if (seasonData == null) {
                    error = true
                    return@forEach
                }
                seasons.add(seasonData)
            }

            show?.seasons = seasons

            //If have success send the complete data to screen
            if (!error) {
                withContext(Dispatchers.Main) {
                    _screenState.value = ScreenState.SUCCESS
                    showItem.value = show
                }
            }
        } else {
            onError(showResult)
        }
    }

    private suspend fun fetchSeason(showId: Int, seasonNumber: Int): Season? {

        val seasonResult = ApiCaller().safeApiCall(Dispatchers.IO) {
            tmdbApi.fetchSeasonAsync(showId, seasonNumber)
        }

        return if (seasonResult is NetworkResult.Success) {
            seasonResult.value
        } else {
            onError(seasonResult)
            null
        }
    }

    private suspend fun <T> onError(result: NetworkResult<T>) {
        var state: ScreenState = ScreenState.GENERIC_ERROR

        when (result) {
            is NetworkResult.NetworkError -> {
                state = ScreenState.NETWORK_ERROR
            }
            is NetworkResult.GenericError -> {
                state = ScreenState.GENERIC_ERROR
            }
            else -> {}
        }

        withContext(Dispatchers.Main) {
            _screenState.value = state
        }
    }

}