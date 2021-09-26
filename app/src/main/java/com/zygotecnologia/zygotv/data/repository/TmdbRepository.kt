package com.zygotecnologia.zygotv.data.repository

import com.zygotecnologia.zygotv.data.source.services.TmdbApi
import com.zygotecnologia.zygotv.data.source.services.TmdbClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TmdbRepository {

    private val tmdbApi = TmdbClient

    suspend fun fetchGenresAsync() = withContext(Dispatchers.IO){
        tmdbApi.getApi().fetchGenresAsync(apiKey = TmdbApi.TMDB_API_KEY, region = TmdbApi.TMDB_REGION)
    }

    suspend fun fetchPopularShowsAsync() = withContext(Dispatchers.IO){
        tmdbApi.getApi().fetchPopularShowsAsync(apiKey = TmdbApi.TMDB_API_KEY, region = TmdbApi.TMDB_REGION)
    }

    suspend fun fetchShowAsync(id: Int) = withContext(Dispatchers.IO){
        tmdbApi.getApi().fetchShowAsync(apiKey = TmdbApi.TMDB_API_KEY, id = id)
    }

    suspend fun fetchSeasonAsync(id: Int, seasonNumber: Int) = withContext(Dispatchers.IO){
        tmdbApi.getApi().fetchSeasonAsync(id = id, seasonNumber = seasonNumber, apiKey = TmdbApi.TMDB_API_KEY)
    }
}