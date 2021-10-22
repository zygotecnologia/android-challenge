package com.zygotecnologia.zygotv.tmdb.data

import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.GenreResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService
import com.zygotecnologia.zygotv.tmdb.domain.Genre
import com.zygotecnologia.zygotv.tmdb.domain.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository

class TmdbRepositoryImpl(
    private val tmdbService: TmdbService
) : TmdbRepository {

    /**
     * Returns a list of shows.
     *
     * The shows are sorted by popularity by default.
     */
    override suspend fun getShows(): List<Show> {
        val genres = getGenres()
        return getShows(genres)
    }

    /**
     * Returns a list of genres with each containing a list of shows from such genre.
     *
     * The same show can appear in multiple genres, and genres might not contain any show.
     */
    override suspend fun getShowsByGenre(): List<GenreWithShows> {
        val genres = getGenres()
        val allShows = getShows(genres)

        return genres.map { genre ->
            val shows = allShows.filter { show ->
                show.genres.contains(genre)
            }

            GenreWithShows(genre, shows)
        }
    }

    /**
     * Returns the most popular show listed at TMDB.
     *
     * Since the returned list is already sorted by popularity, the first item in the list is selected.
     */
    override suspend fun getMostPopularShow(): Show {
        val shows = getShows(getGenres())
        return shows.first()
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
