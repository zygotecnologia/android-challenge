package com.zygotecnologia.zygotv.service.remote.repository.serie

import com.zygotecnologia.zygotv.service.remote.data.serie.ShowResponse
import com.zygotecnologia.zygotv.common.uistate.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author allef.santos on 14/03/21
 */
interface SerieRepository {

    suspend fun getGenres(): Flow<Resource<List<ShowResponse>>>
}