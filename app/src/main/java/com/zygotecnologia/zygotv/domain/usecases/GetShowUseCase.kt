package com.zygotecnologia.zygotv.domain.usecases

import com.zygotecnologia.zygotv.data.model.show.Show
import com.zygotecnologia.zygotv.domain.TVRepository

class GetShowUseCase(private val repository: TVRepository) {

    suspend fun execute(tvId : Int): Show {

        return repository.getShow(tvId)
    }
}