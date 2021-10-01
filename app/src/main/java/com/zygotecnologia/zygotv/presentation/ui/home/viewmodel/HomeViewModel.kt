package com.zygotecnologia.zygotv.presentation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zygotecnologia.zygotv.data.model.genre.Genre
import com.zygotecnologia.zygotv.data.model.show.Show
import com.zygotecnologia.zygotv.domain.usecases.GetListOfGenreUseCase
import com.zygotecnologia.zygotv.domain.usecases.GetListOfShowUseCase
import com.zygotecnologia.zygotv.utils.Constants.favoriteKey
import com.zygotecnologia.zygotv.utils.Constants.region

class HomeViewModel (
    private val getListOfGenreUseCase: GetListOfGenreUseCase,
    private val getListOfShowUseCase: GetListOfShowUseCase
    ) : ViewModel() {

    fun getShows() = liveData {
        val mapOfShows: MutableMap<String, MutableList<Show>> = mutableMapOf()

        val genres = getListOfGenreUseCase.execute(forRegion = region)

        val unfilteredShows = getListOfShowUseCase.execute(forRegion = region)

        val filteredShows = filterShows(unfilteredShows, genres)

        mapOfShows.setMostLovedShowInFirst(filteredShows)

        mapOfShows.sortFirstBySecond(filteredShows, genres)

        emit(mapOfShows)
    }

    private fun filterShows(unfilteredList: List<Show>, filterList: List<Genre>): List<Show> =
        unfilteredList.map { show ->
            show.copy(genres = filterList.filter { show.genreIds?.contains(it.id) == true })
        }


    private fun MutableMap<String, MutableList<Show>>.setMostLovedShowInFirst(list : List<Show>){
            val show = mutableListOf(list.maxByOrNull { it.voteCount ?: 0 } ?: return)
            this[favoriteKey] = show
    }

    private fun MutableMap<String, MutableList<Show>>.sortFirstBySecond(first : List<Show>, second : List<Genre>){
        second.forEach { genre ->
            first.forEach { show ->
                if (show.genres?.get(0)?.name?.equals(genre.name) == true) {
                    if (this.isNullOrEmpty() || this[genre.name].isNullOrEmpty())
                        this[genre.name!!] = mutableListOf()
                    this[genre.name]?.add(show)
                }
            }
        }
    }
}
