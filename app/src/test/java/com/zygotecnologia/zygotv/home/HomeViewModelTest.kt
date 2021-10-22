package com.zygotecnologia.zygotv.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zygotecnologia.zygotv.test.fake.FakeTmdbRepository
import com.zygotecnologia.zygotv.test.fake.buildShowWith
import com.zygotecnologia.zygotv.test.getOrAwaitValue
import com.zygotecnologia.zygotv.test.rules.MainCoroutineRule
import com.zygotecnologia.zygotv.tmdb.domain.Show
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
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

    private val mostPopularShow: Show = buildShowWith(name = "Squid Game")

    private fun getViewModel() = HomeViewModel(
        tmdbRepository = FakeTmdbRepository(
            mostPopularShow = mostPopularShow
        )
    )

    @Test
    fun `viewModel should load show list`() = runBlockingTest {
        val viewModel = getViewModel()

        viewModel.showsByGenre.getOrAwaitValue().shouldNotBeEmpty()
    }

    @Test
    fun `mostPopularShow should be Squid Game`() = runBlockingTest {
        val viewModel = getViewModel()

        viewModel.mostPopularShow.getOrAwaitValue().name shouldBe "Squid Game"
    }
}
