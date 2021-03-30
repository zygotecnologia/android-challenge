package com.zygotecnologia.zygotv.datasource
import com.zygotecnologia.zygotv.api.TmdbApi
import com.zygotecnologia.zygotv.model.*
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: TmdbApi): RemoteDataSource {
    override suspend fun getGenres(apikey: String, region: String): List<Genre> = api.fetchGenresAsync(apikey, region).genres

    override suspend fun getPopularShow(apikey: String, region: String): ShowResponse = api.fetchPopularShowsAsync(apikey, region)

    override suspend fun getShow(id: Int, apikey: String): ShowDetails = api.fetchShowAsync(id, apikey)

    override suspend fun getSeason(id: Int, seasonNumber: Int, apikey: String): Season = api.fetchSeasonAsync(id, seasonNumber, apikey)

    override suspend fun getEpisode(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int,
        apikey: String
    ): Episode =
        api.fetchEpisodeAsync(id, seasonNumber, episodeNumber, apikey)
}