package com.zygotecnologia.zygotv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.zygotecnologia.zygotv.model.Episode
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.ShowsRepository
import com.zygotecnologia.zygotv.ui.detail.DetailViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class DetailViewModelUnitTest {

    private val episode1 = Episode(
        episodeNumber = 1,
        name = "Episode 1",
        overview = "Episode 1 overview",
        seasonNumber = 1
    )

    private val episode2 = Episode(
        episodeNumber = 2,
        name = "Episode 2",
        overview = "Episode 2 overview",
        seasonNumber = 1
    )

    private val season1 = Season(
        episodes = listOf(episode1, episode2),
        overview = "Season overview",
        postPath = null,
        seasonNumber = 1
    )

    private val showDetail = Show(
        id = 1,
        genreIds = listOf(1),
        name = "show1",
        originalLanguage = null,
        originalName = null,
        voteCount = null,
        backdropPath = null,
        overview = null,
        posterPath = null,
        numberOfSeasons = 1,
        seasons = null
    )

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val showsRepository = mockk<ShowsRepository>()

    private val viewModel = DetailViewModel(showsRepository)

    @Test
    fun `When is requested the show details Then the show must be returned`() {
        coEvery { showsRepository.fetchShow(any()) }.coAnswers { showDetail }
        coEvery { showsRepository.fetchSeason(any(), any()) }.coAnswers { season1 }
        runBlocking { viewModel.loadShow(1) }
        val composedShowDetail = showDetail.copy(seasons = listOf(season1))
        Truth.assertThat(viewModel.show.value).isEqualTo(composedShowDetail)
    }

    @Test
    fun `Given there is no connection When is requested the show details Then an error message must be shown`() {
        coEvery { showsRepository.fetchShow(any()) }.throws(IOException())
        runBlocking { viewModel.loadShow(1) }
        Truth.assertThat(viewModel.screenStatus.value).isEqualTo(
            DetailViewModel.ScreenStatus(isLoading = false, isError = true)
        )
    }

    @Test
    fun `When there is an error loading the show seasons Then an error message must be shown`() {
        coEvery { showsRepository.fetchShow(any()) }.coAnswers { showDetail }
        coEvery { showsRepository.fetchSeason(any(), any()) }.throws(IOException())
        runBlocking { viewModel.loadShow(1) }
        Truth.assertThat(viewModel.screenStatus.value).isEqualTo(
            DetailViewModel.ScreenStatus(isLoading = false, isError = true)
        )
    }
}