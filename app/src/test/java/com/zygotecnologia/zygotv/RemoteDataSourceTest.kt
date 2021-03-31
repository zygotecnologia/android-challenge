package com.zygotecnologia.zygotv

import com.zygotecnologia.zygotv.api.TmdbApi
import com.zygotecnologia.zygotv.datasource.RemoteDataSourceImpl
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.GenreResponse
import com.zygotecnologia.zygotv.model.ShowResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class RemoteDataSourceTest {
    private val api : TmdbApi = mockk()

    @Test
    fun testeFetchPopularShow() {
        val apiKey = "234"
        val region = "BR"
        val popularShow : ShowResponse = mockk()
        val remoteData = RemoteDataSourceImpl(api)

        coEvery { api.fetchPopularShowsAsync(apiKey, region) } returns popularShow
        runBlocking {
            remoteData.getPopularShow(apiKey, region)
        }
        coVerify { api.fetchPopularShowsAsync(apiKey, region)  }
    }
}