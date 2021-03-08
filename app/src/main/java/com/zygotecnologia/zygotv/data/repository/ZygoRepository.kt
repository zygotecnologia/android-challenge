package com.zygotecnologia.zygotv.data.repository

import com.zygotecnologia.zygotv.data.remote.TmdbApi


class ZygoRepository(private val api : TmdbApi) {
    suspend fun getSeasonsEpisodesDetails(tvId : Int , seasonNumber : Int) = api.fetchSeasonDetails(tvId,seasonNumber)
    suspend fun getSeasonsDetails(tvId : Int) = api.fetchShowAsync(tvId)
    suspend fun getGenders(region : String) = api.fetchGenresAsync(region)
    suspend fun getSerie(region: String) = api.fetchPopularShowsAsync(region)
}