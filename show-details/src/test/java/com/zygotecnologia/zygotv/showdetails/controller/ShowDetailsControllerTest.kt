package com.zygotecnologia.zygotv.showdetails.controller

import com.nhaarman.mockitokotlin2.*
import com.zygotecnologia.zygotv.showdetails.Season
import com.zygotecnologia.zygotv.showdetails.ShowDetails
import com.zygotecnologia.zygotv.showdetails.presenter.SeasonsPresenter
import com.zygotecnologia.zygotv.showdetails.presenter.ShowDetailsPresenter
import com.zygotecnologia.zygotv.showdetails.repository.ShowDetailsRepository
import com.zygotecnologia.zygotv.showdetails.repository.ShowSeasonRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ShowDetailsControllerTest {
    private val showDetailsRepository = mock<ShowDetailsRepository>()
    private val showSeasonRepository = mock<ShowSeasonRepository>()
    private val showDetailsPresenter = mock<ShowDetailsPresenter>()
    private val seasonsPresenter = mock<SeasonsPresenter>()
    private val controller = ShowDetailsController(
        showDetailsRepository,
        showSeasonRepository,
        showDetailsPresenter,
        seasonsPresenter
    )

    private val seasonMock = Season(1, "name", "overView", "posterImageUrl", listOf())

    private val showDetailsMock = ShowDetails(
        10, "nome", "backDropUrl",
        listOf(1)
    )

    @Test
    fun `given repository answered entities, expect presenters `() = runBlocking {
        whenever(showDetailsRepository.get(10)).doReturn(showDetailsMock)
        whenever(showSeasonRepository.get(10, 1)).doReturn(seasonMock)
        controller.fetchItems(10).join()

        verify(showDetailsPresenter).present(showDetailsMock)
        verify(seasonsPresenter).present(listOf(seasonMock))
    }

    //TODO: Tratamento de erro
//    @Test(expected = IOException::class)
//    fun `given repository thrown exception, expect error presented`() = runBlocking {
//        whenever(repository.get(10)).doThrow(IOException())
//        controller.fetchItems(10)
//    }
}