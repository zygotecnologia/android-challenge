package com.zygotecnologia.zygotv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zygotecnologia.zygotv.api.TmdbApi
import com.zygotecnologia.zygotv.model.ApiResponse
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowResponse
import com.zygotecnologia.zygotv.usecase.DashboardUseCase
import com.zygotecnologia.zygotv.viewmodel.DashboardViewModel
import io.mockk.*
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ViewModelTest {
    private val useCase : DashboardUseCase = mockk()
    private var viewModel = DashboardViewModel (useCase)

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun testeFetchMostPopular(){
        val showResponse: ShowResponse = mockk()
        val apiSucess = ApiResponse.Sucess(showResponse)
        val list = listOf<Show>()

        coEvery { useCase.getPopularShow(TmdbApi.TMDB_API_KEY, TmdbApi.TMDB_API_REGION) } returns apiSucess
        every { apiSucess.data.results } returns list

        runBlocking {
            viewModel.loadListOfShows()
        }

        coVerify { useCase.getPopularShow(TmdbApi.TMDB_API_KEY, TmdbApi.TMDB_API_REGION) }

        assertEquals(viewModel.mutableListOfShowDetails.value,list)
    }
}