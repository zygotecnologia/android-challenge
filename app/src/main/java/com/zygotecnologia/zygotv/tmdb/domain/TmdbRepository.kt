package com.zygotecnologia.zygotv.tmdb.domain

interface TmdbRepository {

    suspend fun getShows(): List<Show>

    suspend fun getShowsByGenre(): List<GenreWithShows>

    suspend fun getMostPopularShow(): Show
}