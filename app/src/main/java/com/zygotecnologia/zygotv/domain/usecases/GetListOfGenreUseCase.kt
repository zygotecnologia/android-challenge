package com.example.zygotv.domain.usecases

import com.example.zygotv.data.model.genre.Genre
import com.example.zygotv.domain.TVRepository

class GetListOfGenreUseCase(private val repository: TVRepository) {

    suspend fun execute(forRegion : String): List<Genre> {

        return repository.getGenreList(forRegion)
    }
}