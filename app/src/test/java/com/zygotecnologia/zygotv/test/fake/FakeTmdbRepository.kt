package com.zygotecnologia.zygotv.test.fake

import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.NetworkResult
import com.zygotecnologia.zygotv.test.asSuccess
import com.zygotecnologia.zygotv.tmdb.domain.Genre
import com.zygotecnologia.zygotv.tmdb.domain.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository

class FakeTmdbRepository(
    private val mostPopularShow: Show = showWith(
        id = 1,
        name = "You"
    )
) : TmdbRepository {

    override suspend fun getShow(showId: Int): NetworkResult<Show> = showWith(
        id = showId,
    ).asSuccess()

    override suspend fun getShowsByGenre() = listOf(
        GenreWithShows(
            genre = genreWith(id = 1, name = "Drama"),
            shows = listOf(
                mostPopularShow,
                showWith(
                    id = 3,
                    name = "The Good Place"
                )
            )
        )
    ).asSuccess()

    override suspend fun getMostPopularShow() = mostPopularShow.asSuccess()
}

fun showWith(
    id: Int = 1,
    name: String = "Show"
) = Show(
    id = id,
    name = name,
    backdropPath = "${name}backdropPath",
    posterPath = "${name}posterPath"
)

fun genreWith(
    id: Int = 1,
    name: String = "Genre"
) = Genre(
    id = id,
    name = name
)
