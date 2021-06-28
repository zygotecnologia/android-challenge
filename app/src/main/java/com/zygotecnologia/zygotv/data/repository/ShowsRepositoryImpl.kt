package com.zygotecnologia.zygotv.data.repository

import com.zygotecnologia.zygotv.data.entity.toGenreEntity
import com.zygotecnologia.zygotv.data.entity.toSeasonEntity
import com.zygotecnologia.zygotv.data.entity.toShowEntity
import com.zygotecnologia.zygotv.data.remote.ShowsRemoteDataSource
import com.zygotecnologia.zygotv.domain.entity.Genre
import com.zygotecnologia.zygotv.domain.entity.Season
import com.zygotecnologia.zygotv.domain.entity.Show
import com.zygotecnologia.zygotv.domain.repository.ShowsRepository

class ShowsRepositoryImpl(
    private val showsDataSource: ShowsRemoteDataSource
): ShowsRepository {

    override suspend fun fetchGenres(): List<Genre>? {
        return showsDataSource.fetchGenres()?.genres?.map {
            it.toGenreEntity()
        }
    }

    override suspend fun fetchPopularShows(): List<Show>? {
        return showsDataSource.fetchPopularShows()?.results?.map {
            it.toShowEntity()
        }
    }

    override suspend fun fetchShowsByGenresId(genresId: List<String>): List<Show>? {
        return showsDataSource.fetchShowsByGenresId(genresId)?.results?.map {
            it.toShowEntity()
        }
    }

    override suspend fun fetchShow(showId: Int): Show? {
        return showsDataSource.fetchShow(showId)?.toShowEntity()
    }

    override suspend fun fetchSeason(showId: Int, seasonNumber: Int): Season? {
        return showsDataSource.fetchSeason(showId, seasonNumber)?.toSeasonEntity()
    }

    override suspend fun fetchShowSearch(searchQuery: String): List<Show>? {
        return showsDataSource.fetchShowSearch(searchQuery)?.results?.map {
            it.toShowEntity()
        }
    }
}