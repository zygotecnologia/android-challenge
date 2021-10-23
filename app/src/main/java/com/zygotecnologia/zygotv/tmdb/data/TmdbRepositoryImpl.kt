package com.zygotecnologia.zygotv.tmdb.data

import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.NetworkResult
import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.dataOrNull
import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.map
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.*
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService
import com.zygotecnologia.zygotv.tmdb.domain.*
import com.zygotecnologia.zygotv.tmdb.domain.episode.Episode
import com.zygotecnologia.zygotv.tmdb.domain.genre.Genre
import com.zygotecnologia.zygotv.tmdb.domain.genre.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.season.Season
import com.zygotecnologia.zygotv.tmdb.domain.season.SeasonWithEpisodes
import com.zygotecnologia.zygotv.tmdb.domain.show.Show
import com.zygotecnologia.zygotv.tmdb.domain.show.ShowWithSeasons
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

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
    override suspend fun getShow(showId: Int): NetworkResult<ShowWithSeasons> = coroutineScope {
        val showWithSeasons = getShowWith(showId).dataOrNull()  ?: return@coroutineScope NetworkResult.Failure

        val seasonsWithEpisodes = showWithSeasons.seasons.map { seasonWithEpisodes ->
            val season = seasonWithEpisodes.season

            async {
                SeasonWithEpisodes(
                    season = season,
                    episodes = getSeasonEpisodes(showId, season.seasonNumber).dataOrNull() ?: emptyList()
                )
            }
        }.awaitAll()

        return@coroutineScope NetworkResult.Success(
            showWithSeasons.copy(
                seasons = seasonsWithEpisodes
            )
        )
    }

    /**
     * Returns a list of genres with each containing a list of shows from such genre.
     *
     * The same show can appear in multiple genres, and genres might not contain any show.
     */
    override suspend fun getShowsByGenre(): NetworkResult<List<GenreWithShows>> {
        val genres = getGenres().dataOrNull() ?: return NetworkResult.Failure
        val allShows = getShowResponses().dataOrNull() ?: return NetworkResult.Failure

        val genresWithShows = genres.map { genre ->
            val shows = allShows
                .filter { it.genreIds.contains(genre.id) }
                .map { it.toShow() }

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
        val shows = getShowResponses().dataOrNull() ?: return NetworkResult.Failure

        val mostPopularShow = shows.first().toShow()

        return NetworkResult.Success(mostPopularShow)
    }

    private suspend fun getShowWith(showId: Int) = tmdbService
        .fetchShowAsync(showId)
        .map { data -> data.toShow() }

    private suspend fun getSeasonEpisodes(
        showId: Int,
        seasonNumber: Int
    ) = tmdbService
        .fetchSeasonDetailsAsync(showId, seasonNumber)
        .map { data -> data.episodes.map { it.toEpisode() } }

    private suspend fun getGenres() = tmdbService
        .fetchGenresAsync()
        .map { data -> data.genreResponses.map { it.toGenre() } }

    private suspend fun getShowResponses() = tmdbService
        .fetchPopularShowsAsync()
        .map { it.results }

    private fun GenreResponse.toGenre() = Genre(
        id = id,
        name = name
    )

    private fun ShowResponse.toShow() = Show(
        id = id,
        name = name,
        backdropPath = backdropPath,
        posterPath = posterPath
    )

    private fun ShowDetailsResponse.toShow() = ShowWithSeasons(
        show = Show(
            id = id,
            name = name,
            backdropPath = backdropPath,
            posterPath = posterPath
        ),
        seasons = seasons.map {
            SeasonWithEpisodes(
                season = it.toSeason(),
                episodes = emptyList()
            )
        }
    )

    private fun SeasonResponse.toSeason() = Season(
        id = id,
        seasonNumber = seasonNumber,
        name = name,
        overview = overview,
        posterPath = posterPath
    )

    private fun EpisodeResponse.toEpisode() = Episode(
        id = id,
        name = name,
        overview = overview
    )
}
