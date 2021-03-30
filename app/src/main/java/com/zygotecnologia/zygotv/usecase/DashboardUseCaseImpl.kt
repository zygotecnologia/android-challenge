package com.zygotecnologia.zygotv.usecase

import com.zygotecnologia.zygotv.model.*
import com.zygotecnologia.zygotv.repository.DashboardMoviesRepository
import javax.inject.Inject

class DashboardUseCaseImpl @Inject constructor(private val repository: DashboardMoviesRepository) :
    DashboardUseCase {
    override suspend fun getGenre(apikey: String, region: String): ApiResponse<List<Genre>> =
        repository.getGenres(apikey, region)

    override suspend fun getPopularShow(apikey: String, region: String): ApiResponse<ShowResponse> =
        repository.getPopularShow(apikey, region)

    override suspend fun getShow(id: Int, apikey: String,): ApiResponse<ShowDetails> =
        repository.getShow(id, apikey)

    override suspend fun getSeason(
        id: Int,
        seasonNumber: Int,
        apikey: String
    ): ApiResponse<Season> =
        repository.getSeason(id,seasonNumber,apikey)

    override suspend fun getEpisode(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int,
        apikey: String
    ): ApiResponse<Episode> =
        repository.getEpisode(id, seasonNumber, episodeNumber, apikey)

}