package com.zygotecnologia.zygotv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.zygotecnologia.zygotv.data.repository.ShowsRepositoryImpl
import com.zygotecnologia.zygotv.domain.entity.Genre
import com.zygotecnologia.zygotv.domain.entity.Show
import com.zygotecnologia.zygotv.view.shows.ShowsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import java.io.IOException

class ShowsViewModelUnitTest {

    private val show1 = Show(
        id = 1,
        genreIds = listOf(1),
        name = "show1",
        originalLanguage = null,
        originalName = null,
        voteCount = null,
        backdropPath = null,
        overview = null,
        posterPath = null,
        numberOfSeasons = null,
        seasons = null
    )

    private val genre1 = Genre(
        id = 1,
        name = "genre1",
        shows = null
    )

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val showsRepository = mockk<ShowsRepositoryImpl>()

    private val viewModel = ShowsViewModel(showsRepository)

    @Test
    fun `When load shows, Then the must popular show most be the first one from the response`() {
        coEvery { showsRepository.fetchPopularShows() }.coAnswers { listOf(show1) }
        coEvery { showsRepository.fetchGenres() }.coAnswers { listOf(genre1) }
        coEvery { showsRepository.fetchShowsByGenresId(listOf("1")) }.coAnswers { any() }
        runBlocking { viewModel.loadShows() }
        Truth.assertThat(viewModel.showsContent.value?.first).isEqualTo(show1)
    }

    @Test
    fun `When load shows and the result is empty, Then the most popular show must be null`() {
        coEvery { showsRepository.fetchGenres() }.coAnswers { listOf(genre1)}
        coEvery { showsRepository.fetchPopularShows() }.coAnswers { emptyList() }
        runBlocking { viewModel.loadShows() }
        Truth.assertThat(viewModel.showsContent.value?.first).isNull()
    }

    @Test
    fun `When load shows throws an exception, Then an error must be shown`() {
        coEvery { showsRepository.fetchGenres() }.throws(IOException())
        coEvery { showsRepository.fetchPopularShows() }.coAnswers { listOf(show1) }
        runBlocking { viewModel.loadShows() }
        Truth.assertThat(viewModel.error.value).isTrue()
    }
}