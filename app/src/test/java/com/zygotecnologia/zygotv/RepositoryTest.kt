package com.zygotecnologia.zygotv

import com.zygotecnologia.zygotv.datasource.RemoteDataSource
import com.zygotecnologia.zygotv.model.ApiResponse
import com.zygotecnologia.zygotv.model.ShowResponse
import com.zygotecnologia.zygotv.repository.DashboardMoviesRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
class RepositoryTest {
    val remoteData: RemoteDataSource = mockk()
    val repository = DashboardMoviesRepositoryImpl(remoteData)

    @Test
    fun testeFetchPopularShowSucess(){
        val apiKey = "234"
        val region = "BR"
        val showResponse : ShowResponse = mockk()
        val apiResponse: ApiResponse<ShowResponse>
        coEvery { remoteData.getPopularShow(apiKey, region) } returns showResponse

        runBlocking { apiResponse = repository.getPopularShow(apiKey, region) }

        coVerify { remoteData.getPopularShow(apiKey, region)  }
        assertTrue(apiResponse is ApiResponse.Sucess)
        assertEquals(showResponse,(apiResponse as ApiResponse.Sucess).data)
    }

    @Test
    fun testeFetchPopularShowFailure() {
        val apiKey = "234"
        val region = "BR"
        val exceptionTest = Exception("just a test")
        val apiResponse: ApiResponse<ShowResponse>
        coEvery { remoteData.getPopularShow(apiKey, region) } throws exceptionTest

        runBlocking { apiResponse = repository.getPopularShow(apiKey, region) }

        coVerify { remoteData.getPopularShow(apiKey, region)  }
        assertTrue(apiResponse is ApiResponse.Failure)
        assertEquals(exceptionTest,(apiResponse as ApiResponse.Failure).exception)
    }
}