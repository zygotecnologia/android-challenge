package com.zygotecnologia.zygotv.tmdb.domain

import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.NetworkResult

interface TmdbRepository {

    suspend fun getShow(showId: Int): NetworkResult<ShowWithSeasons>

    suspend fun getShowsByGenre(): NetworkResult<List<GenreWithShows>>

    suspend fun getMostPopularShow(): NetworkResult<Show>
}