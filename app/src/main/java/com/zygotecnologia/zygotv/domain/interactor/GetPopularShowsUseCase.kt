package com.zygotecnologia.zygotv.domain.interactor

import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.domain.repository.ZygoRepository
import kotlinx.coroutines.flow.Flow

class GetPopularShowsUseCase(private val repository: ZygoRepository) {
    fun execute(genres: List<Genre>): Flow<List<Show>> {
        return repository.getPopularShows(genres)
    }
}