package com.zygotecnologia.zygotv.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zygotecnologia.zygotv.test.FakeTmdbService
import com.zygotecnologia.zygotv.test.getOrAwaitValue
import com.zygotecnologia.zygotv.test.rules.MainCoroutineRule
import io.kotest.matchers.collections.shouldNotBeEmpty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()


    private fun getViewModel() = MainViewModel(
        tmdbService = FakeTmdbService()
    )

    @Test
    fun `viewModel should load show list`() = runBlockingTest {
        val viewModel = getViewModel()

        viewModel.shows.getOrAwaitValue().shouldNotBeEmpty()
    }
}
