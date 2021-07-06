package com.zygotecnologia.zygotv.showdetails.repository

import com.zygotecnologia.zygotv.showdetails.ShowDetails
import com.zygotecnologia.zygotv.themoviedbapi.tv.TvShowByIdAPI
import com.zygotecnologia.zygotv.themoviedbapi.tv.season.TvSeasonsAPI
import java.io.IOException
import kotlin.jvm.Throws

internal class ShowDetailsRepositoryImpl(
    private val tvShowByIdAPI: TvShowByIdAPI
) : ShowDetailsRepository {

    @Throws(IOException::class)
    override suspend fun get(id: Int): ShowDetails = tvShowByIdAPI.get(id).let { ShowDetails(it.id ?: 0, it.name ?: "", it.backdropPath ?: "", it.seasons?.map { it.seasonNumber?: 0 } ?: emptyList()) }
}