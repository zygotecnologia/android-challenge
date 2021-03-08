package com.zygotecnologia.zygotv.network.api.repository

import com.zygotecnologia.zygotv.model.genre.GenreResponse
import com.zygotecnologia.zygotv.model.season.SeasonResponse
import com.zygotecnologia.zygotv.model.show.ShowDetails
import com.zygotecnologia.zygotv.model.show.ShowResponse
import com.zygotecnologia.zygotv.network.model.Resource
import retrofit2.http.Path

interface ApiRepository {

    suspend fun fetchGenres() : Resource<GenreResponse>
    suspend fun fetchPopularShows() : Resource<ShowResponse>
    suspend fun fetchShow(@Path("id") id: Int) : Resource<ShowDetails>
    suspend fun fetchSeasonDetails(@Path("id") id: Int,
                                   @Path("season_number") seasonNumber: Int) : Resource<SeasonResponse>

}