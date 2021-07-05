package com.zygotecnologia.zygotv.showdetails.controller

import com.nhaarman.mockitokotlin2.mock
import com.zygotecnologia.zygotv.showdetails.repository.ShowDetailsRepository
import org.junit.Test

class ShowDetailsControllerTest {
    private val repository = mock<ShowDetailsRepository>()
    private val controller = ShowDetailsController(repository)

    @Test
    fun test() {

    }
}