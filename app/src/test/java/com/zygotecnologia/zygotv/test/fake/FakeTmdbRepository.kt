package com.zygotecnologia.zygotv.test.fake

import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.NetworkResult
import com.zygotecnologia.zygotv.test.asSuccess
import com.zygotecnologia.zygotv.tmdb.domain.*

class FakeTmdbRepository(
    private val showWithSeasons: ShowWithSeasons = showWithSeasonsWith(),
    private val mostPopularShow: Show = showWith(
        id = 2,
        name = "You"
    ),
    private val otherShows: List<Show> = listOf(
        showWith(
            id = 3,
            name = "The Good Place"
        )
    )
) : TmdbRepository {

    override suspend fun getShow(showId: Int): NetworkResult<ShowWithSeasons> = showWithSeasons.asSuccess()

    override suspend fun getShowsByGenre() = listOf(
        GenreWithShows(
            genre = genreWith(id = 1, name = "Drama"),
            shows = listOf(mostPopularShow) + otherShows
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

fun seasonWith(
    id: Int = 1,
    name: String = "Season $id"
) = Season(
    id = id,
    seasonNumber = id,
    name = name,
    overview = "$name overview.",
    posterPath = "${name}posterPath"
)

fun episodeWith(
    id: Int = 1,
    name: String = "Episode $id"
) = Episode(
    id = id,
    name = name,
    overview = "$name overview."
)

fun showWithSeasonsWith(
    show: Show = showWith(),
    seasons: List<SeasonWithEpisodes> = listOf(seasonWithEpisodesWith())
) = ShowWithSeasons(
    show = show,
    seasons = seasons
)

fun seasonWithEpisodesWith(
    season: String = "Season 1",
    episodes: List<String> = listOf("Episode 1")
) = SeasonWithEpisodes(
    season = seasonWith(name = season),
    episodes = episodes.map { episodeWith(name = it) }
)
