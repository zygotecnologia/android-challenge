package com.zygotecnologia.zygotv.tmdb.data

import com.zygotecnologia.zygotv.test.FakeTmdbService
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository
import io.kotest.matchers.collections.shouldNotBeEmpty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class TmdbRepositoryImplTest {

    private val repository: TmdbRepository = TmdbRepositoryImpl(
        tmdbService = FakeTmdbService()
    )

    @Test
    fun `getShows should return show list`() = runBlockingTest {
        repository.getShows().shouldNotBeEmpty()
    }
}
