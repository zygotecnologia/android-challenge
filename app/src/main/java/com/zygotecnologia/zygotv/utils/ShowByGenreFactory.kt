package com.zygotecnologia.zygotv.utils

import com.zygotecnologia.zygotv.data.model.ShowDetailsModel.Genre
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.data.model.ShowByGenre

class ShowByGenreFactory() {

    private val showByGenreList: ArrayList<ShowByGenre> = arrayListOf()

    fun generateListOfShowsByGenre(
        genreList: List<Genre>,
        showList: List<Show>
    ): List<ShowByGenre> {


        for (genre in genreList) {
            val showByGenre =
                genre.name?.let {
                    ShowByGenre(
                        genre = it,
                        shows = showList.filter { show ->
                            show.genreIds?.contains(genre.id) == true
                        }
                    )
                }

            if (showByGenre != null) {
                showByGenreList.add(showByGenre)
            }
        }



        return showByGenreList
    }

}