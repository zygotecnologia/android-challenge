package com.zygotecnologia.zygotv.data.repository

import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.data.network.TmdbClient
import com.zygotecnologia.zygotv.model.GenreResponse
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowResponse

class TmdbDataSource(private val webClient: TmdbClient) : TmdbRepository {
    override suspend fun fetchGenreAsync(): GenreResponse? {
        return webClient.getInstance().fetchGenresAsync(
            apiKey = BuildConfig.TMDB_API_KEY,
            region = "BR"
        )
    }

    override suspend fun fetchPopularShowsAsync(): ShowResponse? {
        return webClient.getInstance().fetchPopularShowsAsync(
            apiKey = BuildConfig.TMDB_API_KEY,
            region = "BR"
        )
    }

    override suspend fun fetchShowAsync(id: Int): Show? {
        return webClient.getInstance().fetchShowAsync(
            apiKey = BuildConfig.TMDB_API_KEY,
            id = id
        )
    }
}