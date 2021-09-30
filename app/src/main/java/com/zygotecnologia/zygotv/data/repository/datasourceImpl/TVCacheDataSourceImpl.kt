package com.zygotecnologia.zygotv.data.repository.datasourceImpl

import com.zygotecnologia.zygotv.data.model.genre.Genre
import com.zygotecnologia.zygotv.data.model.show.Show
import com.zygotecnologia.zygotv.data.repository.datasource.TVCacheDataSource
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