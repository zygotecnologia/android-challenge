package com.zygotecnologia.zygotv.data.repository.datasource

import com.zygotecnologia.zygotv.data.model.genre.Genre
import com.zygotecnologia.zygotv.data.model.show.Show

interface TVCacheDataSource {
    suspend fun getGenreListFromCache(): List<Genre>

    suspend fun saveGenreListToCache(genres: List<Genre>)

    suspend fun getShowListFromCache(): List<Show>

    suspend fun saveShowListToCache(shows: List<Show>)
}