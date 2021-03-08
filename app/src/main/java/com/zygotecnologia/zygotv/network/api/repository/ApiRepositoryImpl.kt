package com.zygotecnologia.zygotv.network.api.repository

import android.content.Context
import com.zygotecnologia.zygotv.network.api.service.ApiService
import com.zygotecnologia.zygotv.network.model.RetrofitResponse

class ApiRepositoryImpl(val context: Context, private val api: ApiService) : ApiRepository {

    override suspend fun fetchGenres() = RetrofitResponse(context) { api.fetchGenres() }.result()
    override suspend fun fetchPopularShows() = RetrofitResponse(context) { api.fetchPopularShows() }.result()
    override suspend fun fetchShow(id: Int) = RetrofitResponse(context) { api.fetchShow(id) }.result()
    override suspend fun fetchSeasonDetails(id: Int, seasonNumber: Int)
            = RetrofitResponse(context) { api.fetchSeasonDetails(id, seasonNumber) }.result()

}