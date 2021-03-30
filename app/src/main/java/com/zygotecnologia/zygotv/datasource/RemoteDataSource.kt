package com.zygotecnologia.zygotv.datasource

import com.zygotecnologia.zygotv.model.*

interface RemoteDataSource {

    suspend fun getGenres(apikey: String, region: String): List<Genre>

    suspend fun getPopularShow(apikey: String, region: String): ShowResponse

    suspend fun getShow(id: Int, apikey: String): ShowDetails

    suspend fun getSeason(id:Int, seasonNumber:Int, apikey: String): Season

    suspend fun getEpisode(id:Int, seasonNumber:Int, episodeNumber:Int, apikey: String): Episode

}