package com.example.zygotv.data.repository.datasourceImpl

import com.example.zygotv.data.api.ApiService
import com.example.zygotv.data.model.genre.GenreResponse
import com.example.zygotv.data.model.show.Show
import com.example.zygotv.data.model.show.ShowResponse
import com.example.zygotv.data.repository.datasource.TVRemoteDataSource

class TVRemoteDataSourceImpl(
    private val service: ApiService,
    private val key: String
) : TVRemoteDataSource {
    override suspend fun getGenres(region: String): GenreResponse? =
        service.fetchGenresAsync(key, region)

    override suspend fun getShows(region: String): ShowResponse? =
        service.fetchPopularShowsAsync(key, region)

    override suspend fun getShow(tvId: Int): Show? =
        service.fetchShowAsync(tvId, key)

}