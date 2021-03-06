package com.zygotecnologia.zygotv.network.api.repository

import android.content.Context
import com.zygotecnologia.zygotv.network.api.service.ApiService
import com.zygotecnologia.zygotv.network.retrofit.model.RetrofitResponse

class TmdbApiRepository(val context: Context, private val api: ApiService) : ApiRepository {

    override suspend fun fetchGenres() = RetrofitResponse(context) { api.fetchGenres() }.result()
    override suspend fun fetchPopularShows() = RetrofitResponse(context) { api.fetchPopularShows() }.result()
    override suspend fun fetchShow(id: Int) = RetrofitResponse(context) { api.fetchShow(id) }.result()

}