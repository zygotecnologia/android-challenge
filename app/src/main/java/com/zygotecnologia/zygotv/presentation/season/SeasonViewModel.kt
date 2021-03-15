package com.zygotecnologia.zygotv.presentation.season

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.common.asState
import com.zygotecnologia.zygotv.common.launch
import com.zygotecnologia.zygotv.common.uistate.State
import com.zygotecnologia.zygotv.service.remote.data.seasons.SeasonResponse
import com.zygotecnologia.zygotv.service.remote.repository.season.SeasonRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class SeasonViewModel(private val repository: SeasonRepository) : ViewModel() {

    private val _seasonLiveData = MutableLiveData<State<SeasonResponse>>()

    val seasonLiveData: LiveData<State<SeasonResponse>>
        get() = _seasonLiveData

    fun getSerieSeasons(serieId: Int) {
        launch {
            repository.getSeasons(serieId)
                .onStart {
                    _seasonLiveData.value = State.loading()
                }.map { resource -> State.fromResource(resource) }
                .collect(::checkCollect)
        }
    }


    private fun checkCollect(value: State<SeasonResponse>) {
        when (value) {
            is State.Success -> {
                val seasons =
                    value.data.seasons.filterNot { it.name == SPECIAL_EPISODES }
                val result = value.data.copy(seasons = seasons)
                _seasonLiveData.value = result.asState()
            }
            is State.Error -> {
                _seasonLiveData.value = value.message.asState()
            }
        }
    }

    companion object {
        const val SPECIAL_EPISODES = "Especiais"
    }
}


