package com.example.zygotv.domain.usecases

import com.example.zygotv.data.model.show.Show
import com.example.zygotv.domain.TVRepository

class GetShowUseCase(private val repository: TVRepository) {

    suspend fun execute(tvId : Int): Show {

        return repository.getShow(tvId)
    }
}