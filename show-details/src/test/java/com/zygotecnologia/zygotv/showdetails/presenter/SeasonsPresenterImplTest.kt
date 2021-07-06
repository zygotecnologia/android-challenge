package com.zygotecnologia.zygotv.showdetails.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.zygotecnologia.zygotv.showdetails.Episode
import com.zygotecnologia.zygotv.showdetails.Season
import com.zygotecnologia.zygotv.showdetails.view.EpisodeViewModel
import com.zygotecnologia.zygotv.showdetails.view.SeasonViewModel
import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDatabaseImageUrlBuilder
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SeasonsPresenterImplTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val imageUrlBuilder = mock<TheMovieDatabaseImageUrlBuilder>()
    private val seasonsViewmodelObserver = mock<Observer<List<SeasonViewModel>>>()
    private val presenter = SeasonsPresenterImpl(imageUrlBuilder)

    @Before
    fun setup() {
        whenever(imageUrlBuilder.getPosterCompleteUrl("path")).doReturn("urlPath")
        presenter.getSeasonsObserver().observeForever(seasonsViewmodelObserver)
    }

    @Test
    fun `test season presenter`() {
        presenter.present(
            listOf(
                Season(
                    1, "name", "overview", "path",
                            listOf(Episode("name", "description"))
                )
            )
        )

        verify(seasonsViewmodelObserver).onChanged(
            listOf(
                SeasonViewModel(
                    "Temporada 1",
                    "overview",
                    "urlPath",
                    listOf(EpisodeViewModel("name", "description"))
                )
            )
        )
    }
}