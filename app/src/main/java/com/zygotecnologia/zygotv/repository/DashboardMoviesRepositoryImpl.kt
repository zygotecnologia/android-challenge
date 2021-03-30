package com.zygotecnologia.zygotv.repository

import com.zygotecnologia.zygotv.datasource.RemoteDataSource
import com.zygotecnologia.zygotv.model.*
import java.lang.Exception
import javax.inject.Inject

class DashboardMoviesRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    DashboardMoviesRepository {
    override suspend fun getGenres(apikey: String, region: String): ApiResponse<List<Genre>> {
        return try {
            ApiResponse.Sucess(remoteDataSource.getGenres(apikey, region))
        } catch (e: Exception) {
            ApiResponse.Failure(e)
        }
    }

    override suspend fun getPopularShow(apikey: String, region: String): ApiResponse<ShowResponse> {
        return try {
            ApiResponse.Sucess(remoteDataSource.getPopularShow(apikey, region))
        } catch (e: Exception) {
            ApiResponse.Failure(e)
        }
    }

    override suspend fun getShow(id: Int, apikey: String): ApiResponse<ShowDetails> {
        return try {
            ApiResponse.Sucess(remoteDataSource.getShow(id, apikey))
        } catch (e: Exception) {
            ApiResponse.Failure(e)
        }
    }

    override suspend fun getSeason(
        id: Int,
        seasonNumber: Int,
        apikey: String
    ): ApiResponse<Season> {
        return try {
            ApiResponse.Sucess(remoteDataSource.getSeason(id, seasonNumber, apikey))
        } catch (e: Exception) {
            ApiResponse.Failure(e)
        }
    }

    override suspend fun getEpisode(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int,
        apikey: String
    ): ApiResponse<Episode> {
        return try {
            ApiResponse.Sucess(remoteDataSource.getEpisode(id, seasonNumber,episodeNumber, apikey))
        } catch (e: Exception) {
            ApiResponse.Failure(e)
        }    }

}