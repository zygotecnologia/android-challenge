package com.zygotecnologia.zygotv.showdetails.repository

import com.zygotecnologia.zygotv.showdetails.Season
import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDbAPI

interface ShowSeasonRepository {
    suspend fun get(id: Int, seasonNumber: Int): Season

    companion object Factory {
        fun make(): ShowSeasonRepository = ShowSeasonRepositoryImpl(TheMovieDbAPI.tvSeasonsAPI)
    }
}