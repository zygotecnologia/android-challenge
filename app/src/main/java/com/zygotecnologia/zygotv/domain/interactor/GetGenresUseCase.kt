package com.zygotecnologia.zygotv.domain.interactor

import com.zygotecnologia.zygotv.domain.repository.ZygoRepository
import com.zygotecnologia.zygotv.domain.model.Genre
import kotlinx.coroutines.flow.Flow

class GetGenresUseCase(private val repository: ZygoRepository) {
    fun execute(): Flow<List<Genre>> {
        return repository.getGenres()
    }
}