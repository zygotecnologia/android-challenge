package com.zygotecnologia.zygotv.ui.search.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
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

class SearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val repository = mockk<TmdbRepository>()
    private val responseMocked = FakeAnswerMockkResponse()
    private val listPopularShowObserver = mockk<Observer<List<Show>>>(relaxed = true)

    @ExperimentalCoroutinesApi
    @Test
    fun `when the viewmodel calls loadPopularShow, it should return all popular series`() {
        val viewModel = instantiateViewModel()

        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                repository.fetchPopularShowsAsync()
            } returns responseMocked.getShowResponseForMockRepository()
        }
        viewModel.loadPopularShow()

        coVerify {listPopularShowObserver.onChanged(responseMocked.getShowResponseForMockRepository().results)}
    }

    private fun instantiateViewModel(): SearchViewModel {
        val viewModel = SearchViewModel(repository)
        viewModel.listPopularShow.observeForever(listPopularShowObserver)
        return viewModel
    }

}