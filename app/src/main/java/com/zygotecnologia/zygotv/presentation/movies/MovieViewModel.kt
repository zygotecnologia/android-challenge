package com.zygotecnologia.zygotv.presentation.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zygotecnologia.zygotv.domain.model.ApiResult
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.domain.repository.MoviesRepository

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val loadingEvent: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()
    val movieList: MutableLiveData<List<Show>> = MutableLiveData()

    suspend fun getMovies() {
        loadingEvent.value = true

        moviesRepository.getMovies { result ->
            when (result) {
                is ApiResult.Success -> {
                    movieList.value = result.series

                }
                is ApiResult.ServerError -> {
                    errorLiveData.value = result.message
                }
            }
            loadingEvent.value = false
        }
    }

    class ViewModelFactory(private val repository: MoviesRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                return MovieViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
