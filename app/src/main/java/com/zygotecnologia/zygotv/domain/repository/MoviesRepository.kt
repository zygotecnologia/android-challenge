package com.zygotecnologia.zygotv.domain.repository

import com.zygotecnologia.zygotv.domain.model.ApiResult

interface MoviesRepository {
    suspend fun getMovies(usersResultCallback: (result: ApiResult) -> Unit)
}