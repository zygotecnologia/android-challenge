package com.zygotecnologia.zygotv.repository

import com.zygotecnologia.zygotv.database.MoviesDAO
import com.zygotecnologia.zygotv.model.Episodes
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowResponse
import com.zygotecnologia.zygotv.modelGenre.GenreResponse
import com.zygotecnologia.zygotv.network.TmdbApi
import retrofit2.Call

class MoviesRepository(private val service: TmdbApi, private val moviesDao : MoviesDAO) {

    fun getGenres(): Call<GenreResponse> {
        return service.fetchGenresAsync()
    }

    fun getPopularShow(): Call<ShowResponse> {
        return service.fetchPopularShowsAsync()
    }

    fun getShowByID(tvId: Int): Call<Show> {
        return service.fetchShowAsync(tvId)
    }

    fun getEpisodeAsync(tvId: Int, seasonNumber: Int): Call<Episodes> {
        return service.fetchEpisodeAsync(tvId, seasonNumber)
    }

}
