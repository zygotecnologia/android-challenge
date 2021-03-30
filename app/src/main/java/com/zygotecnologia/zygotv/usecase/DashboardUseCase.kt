package com.zygotecnologia.zygotv.usecase

import com.zygotecnologia.zygotv.model.*

interface DashboardUseCase {

    suspend fun getGenre(apikey: String, region: String):  ApiResponse<List<Genre>>

    suspend fun getPopularShow(apikey: String, region: String): ApiResponse<ShowResponse>

    suspend fun getShow(id: Int, apikey: String): ApiResponse<ShowDetails>

    suspend fun getSeason(id: Int, seasonNumber: Int, apikey: String):ApiResponse<Season>

    suspend fun getEpisode(id: Int, seasonNumber: Int, episodeNumber:Int, apikey: String): ApiResponse<Episode>

}