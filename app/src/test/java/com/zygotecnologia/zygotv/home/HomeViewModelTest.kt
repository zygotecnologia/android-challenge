package com.zygotecnologia.zygotv.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zygotecnologia.zygotv.test.fake.FakeTmdbRepository
import com.zygotecnologia.zygotv.test.getOrAwaitValue
import com.zygotecnologia.zygotv.test.rules.MainCoroutineRule
import io.kotest.matchers.collections.shouldNotBeEmpty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()


    private fun getViewModel() = HomeViewModel(
        tmdbRepository = FakeTmdbRepository()
    )

    @Test
    fun `viewModel should load show list`() = runBlockingTest {
        val viewModel = getViewModel()

        viewModel.showsByGenre.getOrAwaitValue().shouldNotBeEmpty()
    }
}
