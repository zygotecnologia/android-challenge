package com.zygotecnologia.zygotv.data.repository

import android.util.Log
import com.zygotecnologia.zygotv.data.model.genre.Genre
import com.zygotecnologia.zygotv.data.model.show.Show
import com.zygotecnologia.zygotv.data.repository.datasource.TVCacheDataSource
import com.zygotecnologia.zygotv.data.repository.datasource.TVRemoteDataSource
import com.zygotecnologia.zygotv.domain.TVRepository
import java.util.*

class TVRepositoryImpl(private val remoteDataSource: TVRemoteDataSource,
                       private val cacheDataSource: TVCacheDataSource
) : TVRepository {

    override suspend fun getGenreList(region: String): List<Genre> = getGenres(region)

    override suspend fun getShowList(region: String): List<Show> = getShows(region)

    override suspend fun getShow(tvId: Int): Show = getTvShow(tvId)

    private suspend fun getGenres(region: String): List<Genre> {
        lateinit var list: List<Genre>
        try {
            list = cacheDataSource.getGenreListFromCache()
        } catch (e: Exception) {
            Log.i("CACHE-ERROR", e.message.toString())
        }
        if (list.isEmpty()) {
            list = getGenreListFromAPI(region) ?: Collections.emptyList()
            cacheDataSource.saveGenreListToCache(list)
        }
        return list
    }

    private suspend fun getGenreListFromAPI(region: String): List<Genre>? {
        try {
            val response = remoteDataSource.getGenres(region)
            return response?.genres
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
        }
        return Collections.emptyList()
    }

    private suspend fun getShows(region: String): List<Show> {
        lateinit var list: List<Show>
        try {
            list = cacheDataSource.getShowListFromCache()
        } catch (e: Exception) {
            Log.i("CACHE-ERROR", e.message.toString())
        }
        if (list.isEmpty()) {
            list = getShowListFromAPI(region) ?: Collections.emptyList()
            cacheDataSource.saveShowListToCache(list)
        }
        return list
    }

    private suspend fun getShowListFromAPI(region: String): List<Show>? {
        try {
            val response = remoteDataSource.getShows(region)
            return response?.results
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
        }
        return Collections.emptyList()
    }

    private suspend fun getTvShow(tvId: Int): Show {
        lateinit var tvShow: Show

        getShowFromAPI(tvId)?.let {
            tvShow = it
        }
        return tvShow
    }

    private suspend fun getShowFromAPI(tvId: Int): Show? {
        return try {
            remoteDataSource.getShow(tvId)
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
            null
        }
    }
}