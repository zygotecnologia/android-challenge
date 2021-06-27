package com.zygotecnologia.zygotv

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import com.zygotecnologia.zygotv.presentation.MainActivity
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.data.repository.ShowsRepositoryImpl
import com.zygotecnologia.zygotv.presentation.detail.DetailViewModel
import com.zygotecnologia.zygotv.presentation.search.SearchViewModel
import com.zygotecnologia.zygotv.presentation.shows.ShowsViewModel
import com.zygotecnologia.zygotv.utils.SingleLiveEvent
import io.mockk.*
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest

@LargeTest
class ShowFragmentTest: KoinTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    lateinit var showsViewModel: ShowsViewModel
    lateinit var detailViewModel: DetailViewModel
    lateinit var searchViewModel: SearchViewModel
    lateinit var showsRepository: ShowsRepositoryImpl
    lateinit var tmdbApi: TmdbApi

    lateinit var showsContent : SingleLiveEvent<Pair<Show, List<Genre>>>
    lateinit var show: SingleLiveEvent<Show>

    private lateinit var module: Module

    @Before
    fun before() {
        showsViewModel = mockk(relaxed = true)
        detailViewModel = mockk(relaxed = true)
        searchViewModel = mockk(relaxed = true)

        showsRepository = mockk(relaxed = true)
        tmdbApi = mockk(relaxed = true)

        showsContent = SingleLiveEvent()
        show = SingleLiveEvent()

        every { showsViewModel.showsContent } returns showsContent
        every { detailViewModel.show } returns show

        module = module(createdAtStart = true, override = true) {
            single { showsViewModel }
            single { detailViewModel }
            single { searchViewModel }
            single { showsRepository }
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