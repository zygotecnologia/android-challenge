package com.zygotecnologia.zygotv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.zygotecnologia.zygotv.model.Search
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.ShowsRepository
import com.zygotecnologia.zygotv.ui.search.SearchViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class SearchViewModelUnitTest {

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

    private val searchResult = Search(
        results = listOf(show1)
    )

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val showsRepository = mockk<ShowsRepository>()

    private val viewModel = SearchViewModel(showsRepository)

    @Test
    fun `When search for some show by its name, Then the list of shows must be returned`() {
        coEvery { showsRepository.fetchShowSearch(any()) }.coAnswers { searchResult }
        runBlocking { viewModel.searchTvShow("show1") }
        Truth.assertThat(viewModel.results.value).isEqualTo(listOf(show1))
    }

    @Test
    fun `Given there is no connection, When search for some show by its name, Then an error must be returned`() {
        coEvery { showsRepository.fetchShowSearch(any()) }.throws(IOException())
        runBlocking { viewModel.searchTvShow("show1") }
        Truth.assertThat(viewModel.error.value).isTrue()
    }
}