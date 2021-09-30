package com.example.zygotv.data.repository.datasourceImpl

import com.example.zygotv.data.model.genre.Genre
import com.example.zygotv.data.model.show.Show
import com.example.zygotv.data.repository.datasource.TVCacheDataSource
import java.util.*

class TVCacheDataSourceImpl : TVCacheDataSource {
    private var genreList = ArrayList<Genre>()
    private var showList = ArrayList<Show>()

    override suspend fun getGenreListFromCache() = genreList

    override suspend fun getShowListFromCache() = showList

    override suspend fun saveGenreListToCache(genres: List<Genre>) {
        genreList.run {
            clear()
            addAll(genres)
        }
    }

    override suspend fun saveShowListToCache(shows: List<Show>) {
        showList.run {
            clear()
            addAll(shows)
        }
    }
}