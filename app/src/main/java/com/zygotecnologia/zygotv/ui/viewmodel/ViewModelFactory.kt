package com.zygotecnologia.zygotv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zygotecnologia.zygotv.data.repository.BaseRepository
import com.zygotecnologia.zygotv.data.repository.ShowsRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                repository as ShowsRepository
            ) as T
            modelClass.isAssignableFrom(ShowDetailsViewModel::class.java) -> ShowDetailsViewModel(
                repository as ShowsRepository
            )as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}