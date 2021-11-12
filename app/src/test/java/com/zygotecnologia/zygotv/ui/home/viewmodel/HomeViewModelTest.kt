package com.zygotecnologia.zygotv.ui.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.data.repository.TmdbRepository
import com.zygotecnologia.zygotv.model.FakeAnswerMockkResponse
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.util.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val repository = mockk<TmdbRepository>()
    private val responseMocked = FakeAnswerMockkResponse()
    private val posterUrlObserver = mockk<Observer<String>>(relaxed = true)
    private val listShowObserver = mockk<Observer<List<Pair<String, List<Show>>>>>(relaxed = true)

    @ExperimentalCoroutinesApi
    @Test
    fun `when view model calls getUrlImage, it must return a url`() {
        val viewModel = instantiateViewModel()
        val compareUrl ="https://image.tmdb.org/t/p/w780${responseMocked.getShowResponseForMockRepository().results!![0].posterPath}?api_key=${BuildConfig.TMDB_API_KEY}"

        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                repository.fetchPopularShowsAsync()
            } returns responseMocked.getShowResponseForMockRepository()
        }
        viewModel.getUrlImage()

        coVerify {posterUrlObserver.onChanged(compareUrl)}
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when the view model calls loadFullShow, it should return a show list`() {
        val viewModel = instantiateViewModel()
        val listShow = responseMocked.getShowResponseForMockRepository().results!!

        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                repository.fetchGenreAsync()
            } returns responseMocked.getGenreResponseForMockRepository()
        }

        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                repository.fetchPopularShowsAsync()
            } returns responseMocked.getShowResponseForMockRepository()

            viewModel.loadFullShow()

            coVerify { listShowObserver.onChanged(listOf(Pair(first = "Thriller", second = listShow))) }
        }
    }


    private fun instantiateViewModel(): HomeViewModel {
        val viewModel = HomeViewModel(repository)
        viewModel.posterUrl.observeForever(posterUrlObserver)
        viewModel.listShow.observeForever(listShowObserver)
        return viewModel
    }
}