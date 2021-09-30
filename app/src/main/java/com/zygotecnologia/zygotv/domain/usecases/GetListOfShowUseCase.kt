package com.example.zygotv.domain.usecases

import com.example.zygotv.data.model.show.Show
import com.example.zygotv.domain.TVRepository

class GetListOfShowUseCase(private val repository: TVRepository) {

    suspend fun execute(forRegion : String): List<Show> {

        return repository.getShowList(forRegion)
    }
}