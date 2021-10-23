package com.zygotecnologia.zygotv.tmdb.domain

import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.NetworkResult
import com.zygotecnologia.zygotv.tmdb.domain.genre.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.show.Show
import com.zygotecnologia.zygotv.tmdb.domain.show.ShowWithSeasons

interface TmdbRepository {

    suspend fun getShow(showId: Int): NetworkResult<ShowWithSeasons>

    suspend fun getShowsByGenre(): NetworkResult<List<GenreWithShows>>

    suspend fun getMostPopularShow(): NetworkResult<Show>
}