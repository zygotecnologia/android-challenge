package com.zygotecnologia.zygotv.ViewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zygotecnologia.zygotv.data.model.GenreResponse
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.data.model.ShowResponse
import com.zygotecnologia.zygotv.data.repository.ShowsRepository
import com.zygotecnologia.zygotv.network.Resource
import com.zygotecnologia.zygotv.ui.viewmodel.HomeViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository: ShowsRepository = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when call the viewModel getShows function should call the repository getShows function`() {
        val viewModel = instantiateViewModel()

        coEvery { repository.getShows() } returns Resource.Success(mockShowResponse)
        viewModel.getShows()

        coVerify { repository.getShows() }
    }

    @Test
    fun `when call the viewModel getGenres functionshould call the repository getGenres function`() {
        val viewModel = instantiateViewModel()

        coEvery { repository.getGenres() } returns Resource.Success(mockGenreResponse)
        viewModel.getGenres()

        coVerify { repository.getGenres() }

    }

    private fun instantiateViewModel(): HomeViewModel {
        return HomeViewModel(repository)
    }

    private val mockGenreResponse = GenreResponse()

    private val mockShowResponse = ShowResponse(
        page = 1,
        totalResults = 1,
        totalPages = 1,
        results = listOf(
            Show(
                genres = null,
                originalName = "",
                genreIds = listOf(),
                name = "show",
                voteCount = 100,
                backdropPath = "",
                originalLanguage = "",
                id = 1,
                overview = "",
                posterPath = "",
                popularity = ""
            )
        )

    )

}