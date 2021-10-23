package com.zygotecnologia.zygotv.tmdb.data

import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.NetworkResult
import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.dataOrNull
import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.map
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.GenreResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowDetailsResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService
import com.zygotecnologia.zygotv.tmdb.domain.Genre
import com.zygotecnologia.zygotv.tmdb.domain.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository

/**
 * Repository for retrieving information such as shows and genres from TMDB.
 *
 * This implementation does not have a cache, so calling multiple methods might create new network
 * calls everytime.
 */
class TmdbRepositoryImpl(
    private val tmdbService: TmdbService
) : TmdbRepository {

    /**
     * Returns show with respective showId.
     */
    override suspend fun getShow(showId: Int): NetworkResult<Show> {
        return getShowWith(showId)
    }

    /**
     * Returns a list of shows.
     *
     * The shows are sorted by popularity by default.
     */
    override suspend fun getShows(): NetworkResult<List<Show>> {
        val genres = getGenres().dataOrNull() ?: return NetworkResult.Failure
        return getShows(genres)
    }

    /**
     * Returns a list of genres with each containing a list of shows from such genre.
     *
     * The same show can appear in multiple genres, and genres might not contain any show.
     */
    override suspend fun getShowsByGenre(): NetworkResult<List<GenreWithShows>> {
        val genres = getGenres().dataOrNull() ?: return NetworkResult.Failure
        val allShows = getShows(genres).dataOrNull() ?: return NetworkResult.Failure

        val genresWithShows = genres.map { genre ->
            val shows = allShows.filter { show ->
                show.genres.contains(genre)
            }

            GenreWithShows(genre, shows)
        }

        return NetworkResult.Success(genresWithShows)
    }

    /**
     * Returns the most popular show listed at TMDB.
     *
     * Since the returned list is already sorted by popularity, the first item in the list is selected.
     */
    override suspend fun getMostPopularShow(): NetworkResult<Show> {
        val genres = getGenres().dataOrNull() ?: return NetworkResult.Failure
        val shows = getShows(genres).dataOrNull() ?: return NetworkResult.Failure

        val mostPopularShow = shows.first()

        return NetworkResult.Success(mostPopularShow)
    }

    private suspend fun getShowWith(showId: Int) = tmdbService
        .fetchShowAsync(showId, TmdbService.TMDB_API_KEY)
        .map { data -> data.toShow() }

    private suspend fun getGenres() = tmdbService
        .fetchGenresAsync(TmdbService.TMDB_API_KEY, "BR")
        .map { data -> data.genreResponses.map { it.toGenre() } }

    private suspend fun getShows(genres: List<Genre>) = tmdbService
        .fetchPopularShowsAsync(TmdbService.TMDB_API_KEY, "BR")
        .map { data -> data.results.map { it.toShow(genres) } }

    private fun GenreResponse.toGenre() = Genre(
        id = id,
        name = name
    )

    private fun ShowResponse.toShow(
        genres: List<Genre>
    ) = Show(
        id = id,
        name = name,
        backdropPath = backdropPath,
        posterPath = posterPath,
        genres = genres.filter { genreIds.contains(it.id) },
    )

    private fun ShowDetailsResponse.toShow() = Show(
        id = id,
        name = name,
        backdropPath = backdropPath,
        posterPath = posterPath,
        genres = genres.map { it.toGenre() },
    )
}
