package com.zygotecnologia.zygotv.views.details

import androidx.lifecycle.*
import com.zygotecnologia.zygotv.model.entity.Section
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

    //Return section list from memory or call the API
    fun loadShowId(showId: Int): LiveData<Show> {
        if (showItem.value == null) {
            _screenState.value = ScreenState.LOADING

            viewModelScope.launch {
                fetchData(showId)
            }
        }
        return showItem
    }

    private suspend fun fetchData(showId: Int) {
        val showResult = ApiCaller().safeApiCall(Dispatchers.IO) {
            tmdbApi.fetchShowAsync(showId)
        }

        when (showResult) {
            is NetworkResult.NetworkError -> {
                withContext(Dispatchers.Main) {
                    _screenState.value = ScreenState.NETWORK_ERROR
                }
            }
            is NetworkResult.GenericError -> {
                withContext(Dispatchers.Main) {
                    _screenState.value = ScreenState.GENERIC_ERROR
                }
            }
            is NetworkResult.Success -> {
                val show = showResult.value

                withContext(Dispatchers.Main) {
                    _screenState.value = ScreenState.SUCCESS
                    showItem.value = show
                }
            }
        }
    }

}