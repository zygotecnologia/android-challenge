package com.zygotecnologia.zygotv.tmdb.data

import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.dataOrNull
import com.zygotecnologia.zygotv.test.fake.FakeTmdbService
import com.zygotecnologia.zygotv.test.fake.showResponseWith
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowResponse
import com.zygotecnologia.zygotv.tmdb.domain.TmdbRepository
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class TmdbRepositoryImplTest {

    private fun getRepository(
        mostPopularShow: ShowResponse = showResponseWith(name = "You")
    ): TmdbRepository = TmdbRepositoryImpl(
        tmdbService = FakeTmdbService(
            mostPopularShow = mostPopularShow
        )
    )

    @Test
    fun `getShows should return show with seasons and episodes`() = runBlockingTest {
        val repository = getRepository()

        val showWithSeasons = repository.getShow(showId = 5).dataOrNull()
        val seasons = showWithSeasons?.seasons

        showWithSeasons.shouldNotBeNull()
        seasons.shouldNotBeEmpty()
        seasons?.forEach {
            it.episodes.shouldNotBeEmpty()
        }
    }

    @Test
    fun `getShowsByGenre should return shows mapped by genre`() = runBlockingTest {
        val repository = getRepository()

        val showsByGenre = repository.getShowsByGenre().dataOrNull()

        showsByGenre.shouldNotBeEmpty()
    }

    @Test
    fun `getMostPopularShow should return first show from list`() = runBlockingTest {
        val mostPopularShow = showResponseWith(name = "La Casa de Papel")

        val repository = getRepository(mostPopularShow)
        val show = repository.getMostPopularShow().dataOrNull()

        show?.name shouldBe "La Casa de Papel"
    }
}
