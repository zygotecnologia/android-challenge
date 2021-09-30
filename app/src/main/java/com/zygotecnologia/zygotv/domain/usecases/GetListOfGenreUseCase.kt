package com.zygotecnologia.zygotv.domain.usecases

import com.zygotecnologia.zygotv.data.model.genre.Genre
import com.zygotecnologia.zygotv.domain.TVRepository

class GetListOfGenreUseCase(private val repository: TVRepository) {

    suspend fun execute(forRegion : String): List<Genre> {

        return repository.getGenreList(forRegion)
    }
}