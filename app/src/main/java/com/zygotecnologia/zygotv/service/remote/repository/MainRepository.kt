package com.zygotecnologia.zygotv.service.remote.repository

import com.zygotecnologia.zygotv.common.safeCall
import com.zygotecnologia.zygotv.uistate.Resource
import com.zygotecnologia.zygotv.common.asResource
import com.zygotecnologia.zygotv.service.remote.data.GenreResponse
import com.zygotecnologia.zygotv.service.remote.data.ShowResponse
import com.zygotecnologia.zygotv.service.remote.data.ShowResponseList
import com.zygotecnologia.zygotv.service.remote.TmdbApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MainRepository(private val apiService: TmdbApi) {

    suspend fun getGenres(): Flow<Resource<List<ShowResponse>>> {

        val genreResponse: Resource<GenreResponse> = safeCall {
            apiService.fetchGenresAsync(TmdbApi.TMDB_API_KEY, "BR")
        }

        val popularShowsResponseList: Resource<ShowResponseList> = safeCall {
            apiService.fetchPopularShowsAsync(TmdbApi.TMDB_API_KEY, "BR")
        }

        val shows = popularShowsResponseList.data
            ?.results
            ?.map { show ->
                show.copy(genres = genreResponse.data?.genres?.filter { show.genreIds?.contains(it.id) == true })
            }
            ?: emptyList()

        return flowOf(shows.asResource())
    }


}

