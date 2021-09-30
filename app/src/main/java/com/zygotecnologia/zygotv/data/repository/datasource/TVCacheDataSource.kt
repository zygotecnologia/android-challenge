package com.example.zygotv.data.repository.datasource

import com.example.zygotv.data.model.genre.Genre
import com.example.zygotv.data.model.show.Show

interface TVCacheDataSource {
    suspend fun getGenreListFromCache(): List<Genre>

    suspend fun saveGenreListToCache(genres: List<Genre>)

    suspend fun getShowListFromCache(): List<Show>

    suspend fun saveShowListToCache(shows: List<Show>)
}