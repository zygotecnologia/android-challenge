package com.zygotecnologia.zygotv

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.data.remote.ShowsRemoteDataSourceImpl
import com.zygotecnologia.zygotv.data.repository.ShowsRepositoryImpl
import com.zygotecnologia.zygotv.domain.entity.Genre
import com.zygotecnologia.zygotv.domain.entity.Show
import com.zygotecnologia.zygotv.utils.SingleLiveEvent
import com.zygotecnologia.zygotv.view.MainActivity
import com.zygotecnologia.zygotv.view.detail.DetailViewModel
import com.zygotecnologia.zygotv.view.search.SearchViewModel
import com.zygotecnologia.zygotv.view.shows.ShowsViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest

class ZygoTvTests: KoinTest {

    private val show1 = Show(
        id = 1,
        genreIds = listOf(1),
        name = "show1",
        originalLanguage = null,
        originalName = null,
        voteCount = null,
        backdropPath = "",
        overview = null,
        posterPath = null,
        numberOfSeasons = null,
        seasons = null
    )

    private val genre1 = Genre(
        id = 1,
        name = "genre1",
        shows = null
    )

    private lateinit var scenario: ActivityScenario<MainActivity>

    private lateinit var showsViewModel: ShowsViewModel
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var showsRepository: ShowsRepositoryImpl
    private lateinit var showsDataSource: ShowsRemoteDataSourceImpl
    private lateinit var tmdbApi: TmdbApi

    private lateinit var module: Module

    private lateinit var showsContent: SingleLiveEvent<Pair<Show, List<Genre>>>

    @Before
    fun before() {
        MockKAnnotations.init(this)

        showsViewModel = mockk(relaxed = true)
        detailViewModel = mockk(relaxed = true)
        searchViewModel = mockk(relaxed = true)

        showsRepository = mockk(relaxed = true)
        showsDataSource = mockk(relaxed = true)
        tmdbApi = mockk(relaxed = true)

        showsContent = SingleLiveEvent()
        every { showsViewModel.showsContent } returns showsContent

        module = module(createdAtStart = true, override = true) {
            single { showsViewModel }
            single { detailViewModel }
            single { searchViewModel }
            single { showsRepository }
            single { showsDataSource }
            single { tmdbApi }
        }

        loadKoinModules(module)

        scenario = launchActivity()
    }

    @After
    fun after() {
        scenario.close()
        unloadKoinModules(module)
    }

    @Test
    fun verifySearchMenuOption() {
        onView(withId(R.id.ivSearchIcon)).check(matches(isDisplayed()))
        onView(withId(R.id.ivSearchIcon)).perform(click())
        onView(withId(R.id.searchSV)).check(matches(isDisplayed()))
    }

    @Test
    fun searchShow() {
        onView(withId(R.id.ivSearchIcon)).check(matches(isDisplayed()))
        onView(withId(R.id.ivSearchIcon)).perform(click())
        onView(withId(R.id.searchSV)).check(matches(isDisplayed()))
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.searchSV))))
            .perform(ViewActions.typeText("show1"))
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.searchSV))))
            .perform(pressImeActionButton())

        onView(withId(R.id.tvSearchResultTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyMostPopularShow() {
        showsContent.postValue(Pair(show1, listOf(genre1)))
        onView(withId(R.id.tvMostPopularName)).check(matches(isDisplayed()))
        onView(withId(R.id.tvMostPopularName)).check(matches(withText("show1")))
    }

    @Test
    fun goToShowDetail() {
        showsContent.postValue(Pair(show1, listOf(genre1)))

        onView(withId(R.id.ivMostPopularPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.ivMostPopularPoster)).perform(click())

        onView(withId(R.id.elvSeasons)).check(matches(isDisplayed()))
    }
}