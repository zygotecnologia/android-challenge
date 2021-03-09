package com.zygotecnologia.zygotv

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zygotecnologia.zygotv.application.roomTestModule
import com.zygotecnologia.zygotv.database.MoviesDAO
import com.zygotecnologia.zygotv.database.MoviesDatabase
import com.zygotecnologia.zygotv.modelGenre.Genre
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class MoviesDAOTest : KoinTest {

    val moviesDatabase: MoviesDatabase by inject()
    val movieDAO: MoviesDAO by inject()


    @Before()
    fun before() {
        loadKoinModules(roomTestModule)
    }

    @After
    fun after() {
        moviesDatabase.close()
    }

    @Test
    fun testInsert_And_QueryAll() {
        runBlocking {

            val genre = Genre(0, "test")

            movieDAO.insert(genre)

            val requestedData = movieDAO.getShows()

            Assert.assertEquals(genre, requestedData.get(0))
        }
    }

}