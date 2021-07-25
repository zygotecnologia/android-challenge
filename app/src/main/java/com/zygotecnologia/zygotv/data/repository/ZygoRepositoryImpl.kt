package com.zygotecnologia.zygotv.data.repository

import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.data.network.TmdbClient
import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.domain.repository.ZygoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ZygoRepositoryImpl(val tmdbClient: TmdbClient): ZygoRepository {
    override fun getGenres(): Flow<List<Genre>> {
        return flow {
            val genres =
                tmdbClient.getInstance()
                    .fetchGenresAsync(TmdbApi.TMDB_API_KEY, "BR")
                    ?.genres
                    ?: emptyList()
            emit(genres)
        }.flowOn(Dispatchers.IO)
    }

    override fun getPopularShows(genres: List<Genre>): Flow<List<Show>> {
        return flow {
            val shows =
                tmdbClient.getInstance()
                    .fetchPopularShowsAsync(TmdbApi.TMDB_API_KEY, "BR")
                    ?.results
                    ?.map { show ->
                        show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
                    }
                    ?: emptyList()
            emit(shows)
        }.flowOn(Dispatchers.IO)
    }
}