package com.zygotecnologia.zygotv.domain.usecases

import com.zygotecnologia.zygotv.data.model.show.Show
import com.zygotecnologia.zygotv.domain.TVRepository

class GetListOfShowUseCase(private val repository: TVRepository) {

    suspend fun execute(forRegion : String): List<Show> {

        return repository.getShowList(forRegion)
    }
}