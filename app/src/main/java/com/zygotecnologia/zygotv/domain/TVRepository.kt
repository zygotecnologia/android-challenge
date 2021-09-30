package com.zygotecnologia.zygotv.domain

import com.zygotecnologia.zygotv.data.model.genre.Genre
import com.zygotecnologia.zygotv.data.model.show.Show

interface TVRepository  {
    suspend fun getGenreList(region: String): List<Genre>
    suspend fun getShowList(region: String): List<Show>
    suspend fun getShow(tvId: Int): Show
}