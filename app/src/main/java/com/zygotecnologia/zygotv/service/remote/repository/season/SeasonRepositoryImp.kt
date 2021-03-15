package com.zygotecnologia.zygotv.service.remote.repository.season

import com.zygotecnologia.zygotv.common.asResource
import com.zygotecnologia.zygotv.common.safeCall
import com.zygotecnologia.zygotv.service.remote.ServiceApi
import com.zygotecnologia.zygotv.service.remote.data.seasons.SeasonResponse
import com.zygotecnologia.zygotv.common.uistate.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class SeasonRepositoryImp(private val apiService: ServiceApi) : SeasonRepository {

    override suspend fun getSeasons(id: Int): Flow<Resource<SeasonResponse>> {

        val seasonResponse: Resource<SeasonResponse> = safeCall {
            apiService.fetchShowAsync(id, "pt-br")
        }

        return when(seasonResponse){
            is Resource.Success -> {
                val listSeasonUpdate = seasonResponse.data?.seasons?.mapIndexed { index, season ->
                    val episodes = safeCall {
                        apiService.fetchSeasonDetail(id, season.seasonNumber, "pt-br")
                    }
                    return@mapIndexed seasonResponse.data.seasons[index].copy(seasonDetails = episodes.data)

                }?.toList()

                val seasons = listSeasonUpdate?.let { seasonResponse.data.copy(seasons = it) }.asResource<SeasonResponse>()

                return flowOf(seasons)
            }
            is Resource.Failed -> {
                flowOf(Resource.Failed(seasonResponse.errorMessage!!))
            }
            else -> flowOf()
        }


    }


}

