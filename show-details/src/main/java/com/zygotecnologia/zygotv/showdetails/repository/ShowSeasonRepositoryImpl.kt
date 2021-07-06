package com.zygotecnologia.zygotv.showdetails.repository

import com.zygotecnologia.zygotv.showdetails.Episode
import com.zygotecnologia.zygotv.showdetails.Season
import com.zygotecnologia.zygotv.themoviedbapi.tv.season.TvSeasonsAPI

internal class ShowSeasonRepositoryImpl(private val api: TvSeasonsAPI) : ShowSeasonRepository {

    override suspend fun get(id: Int, seasonNumber: Int) =
        api.get(id, seasonNumber).let { seasonModel ->
            Season(
                seasonModel.seasonNumber ?: 0,
                seasonModel.name ?: "",
                seasonModel.overView ?: "",
                seasonModel.posterPath ?: "",
                seasonModel.episodes?.map { Episode(it.name ?: "", it.overview ?: "") }
                    ?: emptyList()
            )
        }

}