package com.zygotecnologia.zygotv.test.fake

import com.zygotecnologia.zygotv.test.asSuccess
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.*
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService

class FakeTmdbService(
    private val mostPopularShow: ShowResponse = showResponseWith(id = 1, name = "You")
) : TmdbService {

    override suspend fun fetchGenresAsync(apiKey: String, region: String) = GenreListResponse(
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

    override suspend fun fetchPopularShowsAsync(apiKey: String, region: String) = ShowsPageResponse(
        page = 0,
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
        id: Int,
        apiKey: String
    ) = showDetailsResponseWith(name = "You").asSuccess()
}

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
    genres: List<GenreResponse> = listOf(genreResponseWith(name = "Drama"))
) = ShowDetailsResponse(
    id = id,
    name = name,
    genres = genres,
    backdropPath = "${name}backdropPath",
    posterPath = "${name}posterPath"
)

fun genreResponseWith(
    id: Int = 1,
    name: String
) = GenreResponse(
    id = id,
    name = name
)
