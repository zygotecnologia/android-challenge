package com.zygotecnologia.zygotv.repository

import com.zygotecnologia.zygotv.model.GenreResponse
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowResponse
import com.zygotecnologia.zygotv.network.TmdbClient
import retrofit2.Response

class SeriesRepository {

    suspend fun getGenres () : Response<GenreResponse?> {
        return TmdbClient.getInstance().fetchGenresAsync()
    }
    suspend fun getShows () : Response<ShowResponse?> {
        return TmdbClient.getInstance().fetchPopularShowsAsync()
    }
    suspend fun getShowDetails(id : Int) : Response<Show?> {
        return TmdbClient.getInstance().fetchShowAsync(id = id)
    }
    suspend fun getSeasonDetails(id: Int, seasonId : Int) : Response<Season> {
        return TmdbClient.getInstance().fetchSeasonAsync(id = id, seasonNumber = seasonId)
    }
    suspend fun getSearchSeries(query : String) : Response<ShowResponse?> {
        return TmdbClient.getInstance().fetchSearchSeries(query = query)
    }
}