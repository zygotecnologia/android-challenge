package com.zygotecnologia.zygotv.main

import io.kotest.matchers.collections.shouldNotBeEmpty
import org.junit.Test

class MainViewModelTest {

    private val viewModel = MainViewModel()

    @Test
    fun `viewModel should load show list`() {
        viewModel.shows.value.shouldNotBeEmpty()
    }
}