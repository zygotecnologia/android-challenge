package com.zygotecnologia.zygotv.presentation.serie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.common.SingleLiveEvent
import com.zygotecnologia.zygotv.common.asState
import com.zygotecnologia.zygotv.common.launch
import com.zygotecnologia.zygotv.common.toState
import com.zygotecnologia.zygotv.service.remote.data.serie.ShowResponse
import com.zygotecnologia.zygotv.service.remote.repository.serie.SerieRepository
import com.zygotecnologia.zygotv.common.uistate.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class SeriesViewModel(private val repository: SerieRepository) : ViewModel() {

    private val _romanceLiveData = MutableLiveData<State<List<ShowResponse>>>()

    private val _highlightsLiveData = MutableLiveData<State<ShowResponse>>()

    private val _highlightsClickLiveData = SingleLiveEvent<State<ShowResponse>>()

    private val _comedyaLiveData = MutableLiveData<State<List<ShowResponse>>>()

    val comedyLiveData: LiveData<State<List<ShowResponse>>>
        get() = _comedyaLiveData

    val highlightsLiveData: LiveData<State<ShowResponse>>
        get() = _highlightsLiveData

    val highlightsClickLiveData: SingleLiveEvent<State<ShowResponse>>
        get() = _highlightsClickLiveData

    val romanceLiveData: LiveData<State<List<ShowResponse>>>
        get() = _romanceLiveData

    init {
        getSeries()
    }

    private fun getSeries() {
        launch {
            repository.getGenres().onStart {
                _highlightsLiveData.value = State.loading()
                _romanceLiveData.value = State.loading()
                _comedyaLiveData.value = State.loading()
            }
                .map { resource -> State.fromResource(resource) }
                .collect { value ->
                    when (value) {
                        is State.Success -> {
                            _highlightsLiveData.value = value.data.first().toState()
                            _comedyaLiveData.value =
                                value.data.filter { it.genreIds!!.contains(10759) }.toList().toState()
                            _romanceLiveData.value =
                                value.data.filter { it.genreIds!!.contains(18) }.toList().toState()
                        }

                        is State.Error ->{
                            _comedyaLiveData.value = value.message.asState()
                            _highlightsLiveData.value = value.message.asState()
                            _romanceLiveData.value = value.message.asState()
                        }
                    }
                }
        }

    }

    fun clickItemHighlights() {
        _highlightsClickLiveData.value = _highlightsLiveData.value
    }
}


