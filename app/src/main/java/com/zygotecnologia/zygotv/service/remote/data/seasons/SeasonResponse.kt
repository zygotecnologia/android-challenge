package com.zygotecnologia.zygotv.service.remote.data.seasons


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonResponse(
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "episode_run_time")
    val episodeRunTime: List<Int>,
    @Json(name = "first_air_date")
    val firstAirDate: String,
    @Json(name = "homepage")
    val homepage: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "in_production")
    val inProduction: Boolean,
    @Json(name = "last_air_date")
    val lastAirDate: String,
    @Json(name = "last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir?,
    @Json(name = "name")
    val name: String,
    @Json(name = "next_episode_to_air")
    val nextEpisodeToAir: NextEpisodeToAir?,
    @Json(name = "number_of_episodes")
    val numberOfEpisodes: Int,
    @Json(name = "number_of_seasons")
    val numberOfSeasons: Int,
    @Json(name = "original_name")
    val originalName: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "seasons")
    val seasons: List<Season>
) {


}
fun SeasonResponse.toSeason(): com.zygotecnologia.zygotv.service.model.Season {
     return com.zygotecnologia.zygotv.service.model.Season(
        backdropPath,
        episodeRunTime,
        firstAirDate,
        homepage,
        id,
        inProduction,
        lastAirDate,
        lastEpisodeToAir,
        name,
        nextEpisodeToAir,
        numberOfEpisodes,
        numberOfSeasons, originalName,
        overview, popularity, posterPath, seasons
    )
}
