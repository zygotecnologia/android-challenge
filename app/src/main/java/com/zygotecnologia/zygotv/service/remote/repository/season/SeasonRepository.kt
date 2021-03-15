package com.zygotecnologia.zygotv.service.remote.repository.season

import com.zygotecnologia.zygotv.service.remote.data.seasons.SeasonResponse
import com.zygotecnologia.zygotv.common.uistate.Resource
import kotlinx.coroutines.flow.Flow

interface SeasonRepository {

    suspend fun getSeasons(id: Int): Flow<Resource<SeasonResponse>>

}

