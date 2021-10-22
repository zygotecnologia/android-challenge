package com.zygotecnologia.zygotv.tmdb.data

import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.GenreResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService
import com.zygotecnologia.zygotv.tmdb.domain.Genre
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository

class TmdbRepositoryImpl(
    private val tmdbService: TmdbService
) : TmdbRepository {

    override suspend fun getShows(): List<Show> {
        val genres = getGenres()
        return getShows(genres)
    }

    override suspend fun getShowsByGenre(): Map<Genre, List<Show>> {
        TODO("Not yet implemented")
    }

    private suspend fun getGenres() = tmdbService
        .fetchGenresAsync(TmdbService.TMDB_API_KEY, "BR")
        .genreResponses
        .map { it.toGenre() }

    private suspend fun getShows(genres: List<Genre>) = tmdbService
        .fetchPopularShowsAsync(TmdbService.TMDB_API_KEY, "BR")
        .results
        .map { it.toShow(genres) }

    private fun GenreResponse.toGenre() = Genre(
        id = id,
        name = name
    )

    private fun ShowResponse.toShow(
        genres: List<Genre>
    ) = Show(
        id = id,
        originalName = originalName,
        name = name,
        voteCount = voteCount,
        originalLanguage = originalLanguage,
        overview = overview,
        backdropPath = backdropPath,
        posterPath = backdropPath,
        genres = genres.filter { genreIds.contains(it.id) },
    )
}
