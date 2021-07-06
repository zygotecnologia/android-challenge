package com.zygotecnologia.zygotv.showdetails.repository

import com.zygotecnologia.zygotv.showdetails.ShowDetails
import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDbAPI
import com.zygotecnologia.zygotv.themoviedbapi.tv.TvShowByIdAPI
import java.io.IOException
import kotlin.jvm.Throws

interface ShowDetailsRepository {
    @Throws(IOException::class)
    suspend fun get(id: Int): ShowDetails

    companion object Factory {
        fun make(): ShowDetailsRepository = ShowDetailsRepositoryImpl(TheMovieDbAPI.tvShowByIdAPI)
    }
}