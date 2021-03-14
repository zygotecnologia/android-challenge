package com.zygotecnologia.zygotv.presentation.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.common.launch
import com.zygotecnologia.zygotv.service.remote.data.ShowResponse
import com.zygotecnologia.zygotv.service.remote.repository.MainRepository
import com.zygotecnologia.zygotv.uistate.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class SeriesViewModel(private val repository: MainRepository) : ViewModel() {

    private val _romanceLiveData = MutableLiveData<State<List<ShowResponse>>>()

    private val _firstSerieLiveData = MutableLiveData<State<ShowResponse>>()

    private val _comediaLiveData = MutableLiveData<State<List<ShowResponse>>>()

    val comediaLiveData: LiveData<State<List<ShowResponse>>>
        get() = _comediaLiveData

    val firstSerieLiveData: LiveData<State<ShowResponse>>
        get() = _firstSerieLiveData

    val romanceLiveData: LiveData<State<List<ShowResponse>>>
        get() = _romanceLiveData

    init {
        getSeries()
    }

    private fun getSeries() {
        launch {
            repository.getGenres().onStart {
                _firstSerieLiveData.value = State.loading()
                _romanceLiveData.value = State.loading()
                _comediaLiveData.value = State.loading()
            }
                .map { resource -> State.fromResource(resource) }
                .collect { value ->
                    when (value) {
                        is State.Success -> {
                            _firstSerieLiveData.value = value.data.first().toState()
                            _comediaLiveData.value =
                                value.data.filter { it.genreIds!!.contains(18) }.toList().toState()
                            _romanceLiveData.value =
                                value.data.filter { it.genreIds!!.contains(18) }.toList().toState()
                        }
                    }
                }
        }

    }
}

private fun ShowResponse.toState(): State<ShowResponse> {
    return State.success(this)

}

private fun <E> List<E>.toState(): State<List<E>> {
    return State.success(this)
}
