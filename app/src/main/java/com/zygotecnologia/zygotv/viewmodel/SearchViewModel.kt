package com.zygotecnologia.zygotv.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel(){
    val searchText  : MutableLiveData<String> = MutableLiveData()

    fun setSearchText(text : String) {
        searchText.postValue(text)
    }
}