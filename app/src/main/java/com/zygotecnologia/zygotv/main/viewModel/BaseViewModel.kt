package com.zygotecnologia.zygotv.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.model.genre.Genre
import com.zygotecnologia.zygotv.model.show.Show
import com.zygotecnologia.zygotv.network.api.repository.ApiRepository
import com.zygotecnologia.zygotv.network.model.RequestError
import com.zygotecnologia.zygotv.network.model.RetrofitResponse
import kotlinx.coroutines.launch

abstract class BaseViewModel(protected val api: ApiRepository) : ViewModel() {

    val loading = MutableLiveData(true)
    val errorDialog = MutableLiveData<RequestError>()

    fun loading(value : Boolean) {
        loading.postValue(value)
    }

}
