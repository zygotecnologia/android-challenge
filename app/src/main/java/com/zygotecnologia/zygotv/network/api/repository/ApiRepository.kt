package com.zygotecnologia.zygotv.network.api.repository

import com.zygotecnologia.zygotv.model.*
import com.zygotecnologia.zygotv.network.retrofit.model.Resource
import retrofit2.http.Path

interface ApiRepository {

    suspend fun fetchGenres() : Resource<GenreResponse>
    suspend fun fetchPopularShows() : Resource<ShowResponse>
    suspend fun fetchShow(@Path("id") id: Int) : Resource<ShowDetails>
    suspend fun fetchSeasonDetails(@Path("id") id: Int,
                                   @Path("season_number") seasonNumber: Int) : Resource<SeasonResponse>

}