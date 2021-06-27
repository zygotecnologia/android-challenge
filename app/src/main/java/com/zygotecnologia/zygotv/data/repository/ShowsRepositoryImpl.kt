package com.zygotecnologia.zygotv.data.repository

import com.zygotecnologia.zygotv.model.*
import com.zygotecnologia.zygotv.data.remote.ShowsRemoteDataSource
import com.zygotecnologia.zygotv.domain.repository.ShowsRepository

class ShowsRepositoryImpl(
    private val showsDataSource: ShowsRemoteDataSource
): ShowsRepository {

    override suspend fun fetchGenres(): GenreResponse? {
        return showsDataSource.fetchGenres()
    }

    override suspend fun fetchPopularShows(): ShowResponse? {
        return showsDataSource.fetchPopularShows()
    }

    override suspend fun fetchShowsByGenresId(genresId: List<String>): ShowResponse? {
        return showsDataSource.fetchShowsByGenresId(genresId)
    }

    override suspend fun fetchShow(showId: Int): Show? {
        return showsDataSource.fetchShow(showId)
    }

    override suspend fun fetchSeason(showId: Int, seasonNumber: Int): Season? {
        return showsDataSource.fetchSeason(showId, seasonNumber)
    }

    override suspend fun fetchShowSearch(searchQuery: String): Search? {
        return showsDataSource.fetchShowSearch(searchQuery)
    }
}