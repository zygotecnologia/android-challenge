package com.zygotecnologia.zygotv.data.repository

import com.ingrid.api_marvel.domain.repository.MoviesRepository
import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.data.network.TmdbClient.service
import com.zygotecnologia.zygotv.domain.model.ApiResult
import com.zygotecnologia.zygotv.domain.model.Show
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MoviesRepositoryImpl : MoviesRepository {

    override suspend fun getMovies(resultCallback: (result: ApiResult) -> Unit) {
        service.fetchPopularShowsAsync(TmdbApi.TMDB_API_KEY, "BR").e
        object : Callback<List<Show>?> {

            override fun onResponse(
                    call: Call<List<Show>?>,

                    response: Response<List<Show>?>,
            ) {
                if (response.isSuccessful) {
                    resultCallback(ApiResult.Success(response.body()))
                }
            }

            override fun onFailure(call: Call<List<Show>?>, t: Throwable) {

                resultCallback(ApiResult.ServerError(message = "Erro de conexão"))
            }

        })
    }
}