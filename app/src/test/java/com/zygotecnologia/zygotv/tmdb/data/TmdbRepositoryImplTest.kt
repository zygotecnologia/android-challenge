package com.zygotecnologia.zygotv.tmdb.data

import com.zygotecnologia.zygotv.test.fake.FakeTmdbService
import com.zygotecnologia.zygotv.test.fake.buildShowResponseWith
import com.zygotecnologia.zygotv.test.fake.buildShowWith
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowResponse
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.maps.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class TmdbRepositoryImplTest {

    private fun getRepository(
        mostPopularShow: ShowResponse = buildShowResponseWith(name = "You")
    ): TmdbRepository = TmdbRepositoryImpl(
        tmdbService = FakeTmdbService(
            mostPopularShow = mostPopularShow
        )
    )

    @Test
    fun `getShows should return show list`() = runBlockingTest {
        val repository = getRepository()

        val shows = repository.getShows()

        shows.shouldNotBeEmpty()
    }

    @Test
    fun `show should contain genres`() = runBlockingTest {
        val repository = getRepository()

        val show = repository.getShows().first()

        show.genres.shouldNotBeEmpty()
    }

    @Test
    fun `getShowsByGenre should return shows mapped by genre`() = runBlockingTest {
        val repository = getRepository()

        repository.getShowsByGenre().shouldNotBeEmpty()
    }

    @Test
    fun `getMostPopularShow should return first show from list returned by service`() = runBlockingTest {
        val mostPopularShow = buildShowResponseWith(name = "La Casa de Papel")

        val repository = getRepository(mostPopularShow)

        repository.getMostPopularShow().name shouldBe "La Casa de Papel"
    }
}
