package com.zygotecnologia.zygotv.test

import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.GenreResponse
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowResponse
import com.zygotecnologia.zygotv.network.TmdbService

class FakeTmdbService : TmdbService {

    override suspend fun fetchGenresAsync(apiKey: String, region: String): GenreResponse? {
        return GenreResponse(
            genres = listOf(
                Genre(
                    id = 1,
                    name = "Drama"
                ),
                Genre(
                    id = 2,
                    name = "Comedy"
                ),
                Genre(
                    id = 3,
                    name = "Action"
                )
            )
        )
    }

    override suspend fun fetchPopularShowsAsync(apiKey: String, region: String): ShowResponse {
        return ShowResponse(
            page = 0,
            totalResults = 5,
            totalPages = 1,
            results = listOf(
                buildShowWith(id = 1, name = "You"),
                buildShowWith(id = 2, name = "Round 6"),
                buildShowWith(id = 3, name = "The Good Place"),
                buildShowWith(id = 4, name = "Brooklyn 99"),
                buildShowWith(id = 5, name = "Stranger Things")
            )
        )
    }

    override suspend fun fetchShowAsync(apiKey: String, id: Int): Show? {
        return buildShowWith(name = "You")
    }
}

private fun buildShowWith(
    id: Int = 1,
    name: String
) = Show(
    id = id,
    name = name,
    originalName = name,
    voteCount = 5,
    overview = "${name} overview.",
    originalLanguage = "EN",
    genres = emptyList(),
    genreIds = emptyList(),
    backdropPath = "${name}backdropPath",
    posterPath = "${name}posterPath"
)