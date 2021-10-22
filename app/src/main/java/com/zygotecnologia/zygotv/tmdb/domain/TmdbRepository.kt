package com.zygotecnologia.zygotv.tmdb.domain

interface TmdbRepository {

    suspend fun getShows(): List<Show>

    suspend fun getShowsByGenre(): Map<Genre, List<Show>>
}