package com.zygotecnologia.zygotv.tmdb.data

import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository

class TmdbRepositoryImpl(
    private val tmdbService: TmdbService
): TmdbRepository {

    override suspend fun getShows(): List<Show> {
        TODO("Not yet implemented")
    }
}
