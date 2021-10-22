package com.zygotecnologia.zygotv.tmdb.data

import com.zygotecnologia.zygotv.test.fake.FakeTmdbService
import com.zygotecnologia.zygotv.test.fake.buildShowResponseWith
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.maps.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class TmdbRepositoryImplTest {

    private val mostPopularShow = buildShowResponseWith(name = "You")

    private val repository: TmdbRepository = TmdbRepositoryImpl(
        tmdbService = FakeTmdbService(
            mostPopularShow = mostPopularShow
        )
    )

    @Test
    fun `getShows should return show list`() = runBlockingTest {
        repository.getShows().shouldNotBeEmpty()
    }

    @Test
    fun `show should contain genres`() = runBlockingTest {
        val show = repository.getShows().first()

        show.genres.shouldNotBeEmpty()
    }

    @Test
    fun `getShowsByGenre should return shows mapped by genre`() = runBlockingTest {
        repository.getShowsByGenre().shouldNotBeEmpty()
    }

    @Test
    fun `getMostPopularShow should return first show from list returned by service`() = runBlockingTest {
        repository.getMostPopularShow().name shouldBe "You"
    }
}
