package com.zygotecnologia.zygotv.network.api.repository

import com.zygotecnologia.zygotv.model.GenreResponse
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.model.ShowResponse
import com.zygotecnologia.zygotv.network.retrofit.model.Resource
import retrofit2.http.Path

interface ApiRepository {

    suspend fun fetchGenres() : Resource<GenreResponse>
    suspend fun fetchPopularShows() : Resource<ShowResponse>
    suspend fun fetchShow(@Path("id") id: Int) : Resource<ShowDetails>

}