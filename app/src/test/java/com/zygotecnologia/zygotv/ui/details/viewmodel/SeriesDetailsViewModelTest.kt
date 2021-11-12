package com.zygotecnologia.zygotv.ui.details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.data.repository.TmdbRepository
import com.zygotecnologia.zygotv.model.FakeAnswerMockkResponse
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.util.CoroutinesTestRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class SeriesDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val repository = mockk<TmdbRepository>()
    private val responseMocked = FakeAnswerMockkResponse()
    private val detailsShowObserver = mockk<Observer<ShowDetails>>(relaxed = true)
    private val posterUrlObserver = mockk<Observer<String>>(relaxed = true)

    @ExperimentalCoroutinesApi
    @Test
    fun `when view model calls backDropUrl, it must return a url`(){
        val viewModel= instantiateViewModel()
        val show = responseMocked.getShowResponseForMockRepository().results!![0]
        val compareUrl ="https://image.tmdb.org/t/p/w780${responseMocked.getShowResponseForMockRepository().results!![0].posterPath}?api_key=${BuildConfig.TMDB_API_KEY}"

        viewModel.backDropUrl(show)

        verify { posterUrlObserver.onChanged(compareUrl)}
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when the view model calls loadDetailsShow, it should return series details`(){
        val viewModel= instantiateViewModel()

        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                repository.fetchShowAsync(1)
            } returns  responseMocked.getShowDetailsResponseForMockRepository()

            viewModel.loadDetailsShow(1)

            coVerify { detailsShowObserver.onChanged(responseMocked.getShowDetailsResponseForMockRepository()) }
        }

    }




    private fun instantiateViewModel(): SeriesDetailsViewModel {
        val viewModel = SeriesDetailsViewModel(repository)
        viewModel.posterUrl.observeForever(posterUrlObserver)
        viewModel.detailsShow.observeForever(detailsShowObserver)
        return viewModel
    }
}