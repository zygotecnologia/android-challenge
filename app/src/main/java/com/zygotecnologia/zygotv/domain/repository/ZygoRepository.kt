package com.zygotecnologia.zygotv.domain.repository

import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.domain.model.Show
import kotlinx.coroutines.flow.Flow

interface ZygoRepository {
    fun getGenres(): Flow<List<Genre>>
    fun getPopularShows(genres: List<Genre>): Flow<List<Show>>
}