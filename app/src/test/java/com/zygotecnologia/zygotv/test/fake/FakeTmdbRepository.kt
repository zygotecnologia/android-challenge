package com.zygotecnologia.zygotv.test.fake

import com.zygotecnologia.zygotv.tmdb.domain.Genre
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository

class FakeTmdbRepository : TmdbRepository {

    override suspend fun getShows(): List<Show> = listOf(
        buildShowWith(
            id = 1,
            name = "You",
            genres = listOf(buildGenreWith(id = 1, name = "Drama"))
        ),
        buildShowWith(
            id = 2,
            name = "Brooklyn 99",
            genres = listOf(buildGenreWith(id = 2, name = "Comedy"))
        ),
        buildShowWith(
            id = 3,
            name = "The Good Place",
            genres = listOf(
                buildGenreWith(id = 1, name = "Drama"),
                buildGenreWith(id = 2, name = "Comedy")
            )
        )
    )

    override suspend fun getShowsByGenre(): Map<Genre, List<Show>> = mapOf(
        buildGenreWith(id = 1, name = "Drama") to listOf(
            buildShowWith(
                id = 3,
                name = "The Good Place",
                genres = listOf(
                    buildGenreWith(id = 1, name = "Drama"),
                    buildGenreWith(id = 2, name = "Comedy")
                )
            )
        )
    )
}

private fun buildShowWith(
    id: Int = 1,
    name: String = "Show",
    genres: List<Genre> = listOf(buildGenreWith())
) = Show(
    id = id,
    name = name,
    originalName = name,
    voteCount = 5,
    overview = "$name overview.",
    originalLanguage = "EN",
    genres = genres,
    backdropPath = "${name}backdropPath",
    posterPath = "${name}posterPath"
)

private fun buildGenreWith(
    id: Int = 1,
    name: String = "Genre"
) = Genre(
    id = id,
    name = name
)
