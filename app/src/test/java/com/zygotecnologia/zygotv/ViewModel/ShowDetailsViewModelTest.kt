package com.zygotecnologia.zygotv.ViewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zygotecnologia.zygotv.data.model.ShowDetailsModel.LastEpisodeToAir
import com.zygotecnologia.zygotv.data.model.ShowDetailsModel.ShowDetailsResponse
import com.zygotecnologia.zygotv.data.repository.ShowsRepository
import com.zygotecnologia.zygotv.network.Resource
import com.zygotecnologia.zygotv.ui.viewmodel.ShowDetailsViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShowDetailsViewModelTest {

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
    fun `when call the viewModel getShowDetails function should call the repository getShowDetails function`() {
        val viewModel = instantiateViewModel()

        coEvery { repository.getShowDetails(any()) } returns Resource.Success(mockShoDetailsResponse)
        viewModel.getShowDetails(1)

        coVerify { repository.getShowDetails(1) }
    }

    private fun instantiateViewModel(): ShowDetailsViewModel {
        return ShowDetailsViewModel(repository)
    }

    private val mockShoDetailsResponse = ShowDetailsResponse(
        "",
        listOf(),
        listOf(),
        "",
        listOf(),
        "",
        1,
        true,
        listOf(),
        "",
        mockk(),
        "",
        listOf(),
        mockk(),
        1,
        1,
        listOf(),
        "",
       "",
        "",
        1.1,
        "",
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        "",
        "",
     "",
        1.1,
        1,
    )

}