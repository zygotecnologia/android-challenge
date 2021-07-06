package com.zygotecnologia.zygotv.showdetails.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.zygotecnologia.zygotv.showdetails.ShowDetails
import com.zygotecnologia.zygotv.showdetails.view.ShowDetailsViewModel
import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDatabaseImageUrlBuilder
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShowDetailsPresenterImplTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val imageUrlBuilder = mock<TheMovieDatabaseImageUrlBuilder>()
    private val detailsViewModelObserver = mock<Observer<ShowDetailsViewModel>>()
    private val presenter = ShowDetailsPresenterImpl(imageUrlBuilder)

    @Before
    fun setup() {
        presenter.getShowDetailsObserver().observeForever(detailsViewModelObserver)
        whenever(imageUrlBuilder.getBackdropCompleteUrl("path")).doReturn("urlPath")
    }


    @Test
    fun `test details presenter`() {
        presenter.present(ShowDetails(10, "Name", "path", listOf()))
        verify(detailsViewModelObserver).onChanged(ShowDetailsViewModel("Name", "urlPath"))
    }
}