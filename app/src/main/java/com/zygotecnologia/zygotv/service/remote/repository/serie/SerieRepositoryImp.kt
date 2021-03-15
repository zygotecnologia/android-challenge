package com.zygotecnologia.zygotv.service.remote.repository.serie

import com.zygotecnologia.zygotv.common.asResource
import com.zygotecnologia.zygotv.common.safeCall
import com.zygotecnologia.zygotv.common.uistate.Resource
import com.zygotecnologia.zygotv.service.remote.ServiceApi
import com.zygotecnologia.zygotv.service.remote.data.serie.GenreResponse
import com.zygotecnologia.zygotv.service.remote.data.serie.ShowResponse
import com.zygotecnologia.zygotv.service.remote.data.serie.ShowResponseList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SerieRepositoryImp(private val apiService: ServiceApi) : SerieRepository {

    override suspend fun getGenres(): Flow<Resource<List<ShowResponse>>> {

        val genreResponse: Resource<GenreResponse> = safeCall {
            apiService.fetchGenresAsync("BR")
        }

        return when (genreResponse) {
            is Resource.Success -> {
                val popularShowsResponseList: Resource<ShowResponseList> = safeCall {
                    apiService.fetchPopularShowsAsync("BR")
                }
                val shows = popularShowsResponseList.data
                    ?.results
                    ?.map { show ->
                        show.copy(genres = genreResponse.data?.genres?.filter {
                            show.genreIds?.contains(
                                it.id
                            ) == true
                        })
                    }
                    ?: emptyList()
                return flowOf(shows.asResource())

            }
            is Resource.Failed -> {
                flowOf(Resource.Failed(genreResponse.errorMessage!!))
            }
            else -> flowOf()
        }

    }


}


