package com.example.zygotv.data.repository.datasource

import com.example.zygotv.data.model.genre.GenreResponse
import com.example.zygotv.data.model.show.Show
import com.example.zygotv.data.model.show.ShowResponse

interface TVRemoteDataSource {
    suspend fun getGenres(region: String): GenreResponse?
    suspend fun getShows(region: String): ShowResponse?
    suspend fun getShow(tvId: Int): Show?
}