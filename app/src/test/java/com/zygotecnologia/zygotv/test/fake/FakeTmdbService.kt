package com.zygotecnologia.zygotv.test.fake

import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.NetworkResult
import com.zygotecnologia.zygotv.test.asSuccess
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.*
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService

class FakeTmdbService(
    private val mostPopularShow: ShowResponse = showResponseWith(id = 1, name = "You")
) : TmdbService {

    override suspend fun fetchGenresAsync() = GenreListResponse(
        genreResponses = listOf(
            genreResponseWith(
                id = 1,
                name = "Drama"
            ),
            genreResponseWith(
                id = 2,
                name = "Comedy"
            ),
            genreResponseWith(
                id = 3,
                name = "Action"
            )
        )
    ).asSuccess()

    override suspend fun fetchPopularShowsAsync(
        page: Int
    ) = ShowsPageResponse(
        page = 1,
        totalResults = 5,
        totalPages = 1,
        results = listOf(
            mostPopularShow,
            showResponseWith(id = 2, name = "Round 6"),
            showResponseWith(id = 3, name = "The Good Place"),
            showResponseWith(id = 4, name = "Brooklyn 99"),
            showResponseWith(id = 5, name = "Stranger Things")
        )
    ).asSuccess()

    override suspend fun fetchShowAsync(
        id: Int
    ) = showDetailsResponseWith(name = "You").asSuccess()

    override suspend fun fetchSeasonDetailsAsync(
        id: Int,
        seasonNumber: Int
    ): NetworkResult<SeasonDetailsResponse> = seasonDetailsResponseWith().asSuccess()
}

fun genreResponseWith(
    id: Int = 1,
    name: String
) = GenreResponse(
    id = id,
    name = name
)

fun showResponseWith(
    id: Int = 1,
    name: String
) = ShowResponse(
    id = id,
    name = name,
    genreIds = listOf(1),
    backdropPath = "${name}backdropPath",
    posterPath = "${name}posterPath"
)

fun showDetailsResponseWith(
    id: Int = 1,
    name: String,
    seasons: List<SeasonResponse> = listOf(seasonResponseWith(id = 1))
) = ShowDetailsResponse(
    id = id,
    name = name,
    seasons = seasons,
    backdropPath = "${name}backdropPath",
    posterPath = "${name}posterPath"
)

fun seasonResponseWith(
    id: Int = 1,
    name: String = "Season $id"
) = SeasonResponse(
    id = id,
    seasonNumber = id,
    name = name,
    overview = "$name overview.",
    posterPath = "${name}posterPath"
)

fun seasonDetailsResponseWith(
    episodes: List<EpisodeResponse> = listOf(episodeResponseWith(id = 1))
) = SeasonDetailsResponse(
    episodes = episodes
)

fun episodeResponseWith(
    id: Int = 1,
    name: String = "Episode $id"
) = EpisodeResponse(
    id = id,
    name = name,
    overview = "$name overview."
)
