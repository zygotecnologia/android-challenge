package com.zygotecnologia.zygotv.test.fake

import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.GenreResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.GenreListResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowsPageResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService

class FakeTmdbService(
    private val mostPopularShow: ShowResponse = buildShowResponseWith(id = 1, name = "You")
) : TmdbService {

    override suspend fun fetchGenresAsync(apiKey: String, region: String): GenreListResponse {
        return GenreListResponse(
            genreResponses = listOf(
                GenreResponse(
                    id = 1,
                    name = "Drama"
                ),
                GenreResponse(
                    id = 2,
                    name = "Comedy"
                ),
                GenreResponse(
                    id = 3,
                    name = "Action"
                )
            )
        )
    }

    override suspend fun fetchPopularShowsAsync(apiKey: String, region: String): ShowsPageResponse {
        return ShowsPageResponse(
            page = 0,
            totalResults = 5,
            totalPages = 1,
            results = listOf(
                mostPopularShow,
                buildShowResponseWith(id = 2, name = "Round 6"),
                buildShowResponseWith(id = 3, name = "The Good Place"),
                buildShowResponseWith(id = 4, name = "Brooklyn 99"),
                buildShowResponseWith(id = 5, name = "Stranger Things")
            )
        )
    }

    override suspend fun fetchShowAsync(apiKey: String, id: Int): ShowResponse {
        return buildShowResponseWith(name = "You")
    }
}

fun buildShowResponseWith(
    id: Int = 1,
    name: String
) = ShowResponse(
    id = id,
    name = name,
    originalName = name,
    voteCount = 5,
    overview = "$name overview.",
    originalLanguage = "EN",
    genreIds = listOf(1),
    backdropPath = "${name}backdropPath",
    posterPath = "${name}posterPath"
)