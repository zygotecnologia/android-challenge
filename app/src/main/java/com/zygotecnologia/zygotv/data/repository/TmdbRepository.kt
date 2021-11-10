package com.zygotecnologia.zygotv.data.repository

import com.zygotecnologia.zygotv.model.GenreResponse
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowResponse

interface TmdbRepository {

    suspend fun fetchGenreAsync() : GenreResponse?

    suspend fun fetchPopularShowsAsync() : ShowResponse?

    suspend fun fetchShowAsync(id: Int) : Show?
}