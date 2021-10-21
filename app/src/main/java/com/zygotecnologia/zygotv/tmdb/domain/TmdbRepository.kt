package com.zygotecnologia.zygotv.tmdb.domain

interface TmdbRepository {

    suspend fun getShows(): List<Show>
}