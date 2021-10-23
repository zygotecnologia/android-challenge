package com.zygotecnologia.zygotv.details.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zygotecnologia.zygotv.test.fake.*
import com.zygotecnologia.zygotv.test.getOrAwaitValue
import com.zygotecnologia.zygotv.test.rules.MainCoroutineRule
import com.zygotecnologia.zygotv.tmdb.domain.season.SeasonWithEpisodes
import com.zygotecnologia.zygotv.tmdb.domain.show.Show
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private fun getViewModel(
        show: Show = showWith(),
        seasons: List<SeasonWithEpisodes> = listOf(seasonWithEpisodesWith())
    ) = DetailsViewModel(
        showId = show.id,
        tmdbRepository = FakeTmdbRepository(
            showWithSeasons = showWithSeasonsWith(show, seasons)
        )
    )

    @Test
    fun `viewModel should load show`() = coroutineRule.dispatcher.runBlockingTest {
        val viewModel = getViewModel(
            show = showWith(name = "Lost")
        )

        val show = viewModel.show.getOrAwaitValue()

        show.name shouldBe "Lost"
    }

    @Test
    fun `viewModel should load show details`() = coroutineRule.dispatcher.runBlockingTest {
        val viewModel = getViewModel(
            seasons = listOf(
                seasonWithEpisodesWith(
                    seasonName = "Especials",
                    episodes = listOf("Especial")
                ),
                seasonWithEpisodesWith(
                    seasonName = "Season 1",
                    episodes = listOf("Episode 1", "Episode 2")
                ),
            )
        )

        val showDetails = viewModel.showDetails.getOrAwaitValue()

        showDetails.size shouldBe 2
    }

    @Test
    fun `viewModel show details should be expanded when selecting season`() = coroutineRule.dispatcher.runBlockingTest {
        val season = seasonWith(name = "Season 1")
        val viewModel = getViewModel(
            seasons = listOf(
                seasonWithEpisodesWith(
                    season = season,
                    episodes = listOf("Episode 1", "Episode 2")
                ),
            )
        )

        viewModel.showDetails.getOrAwaitValue().size shouldBe 1

        viewModel.selectSeason(season)

        viewModel.showDetails.getOrAwaitValue().size shouldBe 3
    }

    @Test
    fun `viewModel show details should collapse when selecting the same season`() = coroutineRule.dispatcher.runBlockingTest {
        val season = seasonWith(name = "Season 1")
        val viewModel = getViewModel(
            seasons = listOf(
                seasonWithEpisodesWith(
                    season = season,
                    episodes = listOf("Episode 1", "Episode 2")
                ),
            )
        )
        viewModel.selectSeason(season)

        viewModel.selectSeason(season)

        viewModel.showDetails.getOrAwaitValue().size shouldBe 1
    }
}
