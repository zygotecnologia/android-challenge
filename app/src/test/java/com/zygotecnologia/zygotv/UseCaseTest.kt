package com.zygotecnologia.zygotv

import com.zygotecnologia.zygotv.model.ApiResponse
import com.zygotecnologia.zygotv.model.ShowResponse
import com.zygotecnologia.zygotv.repository.DashboardMoviesRepository
import com.zygotecnologia.zygotv.usecase.DashboardUseCase
import com.zygotecnologia.zygotv.usecase.DashboardUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class UseCaseTest {
    private val repository : DashboardMoviesRepository = mockk()
    private lateinit var usecase: DashboardUseCase

    @Test
    fun testeUseCaseGetPopularSucess(){
        val apiKey = "234"
        val region = "BR"
        val showResponse: ShowResponse = mockk()
        usecase = DashboardUseCaseImpl(repository)
        val apiSucess = ApiResponse.Sucess(showResponse)
        val apiResponse : ApiResponse<ShowResponse>

        coEvery { repository.getPopularShow(apiKey, region) } returns apiSucess

        runBlocking {
            apiResponse = usecase.getPopularShow(apiKey, region)
        }

        coVerify { repository.getPopularShow(apiKey, region) }
        assertEquals(apiResponse, apiSucess)
    }


    @Test
    fun testeUseCaseGetPopularFailure(){
        usecase = DashboardUseCaseImpl(repository)
        val apiKey = "234"
        val region = "BR"
        val exceptionTest = Exception("just a test")
        val apiFailure = ApiResponse.Failure(exceptionTest)
        val apiError: ApiResponse<ShowResponse>

        coEvery { repository.getPopularShow(apiKey, region) } returns apiFailure

        runBlocking { apiError = usecase.getPopularShow(apiKey, region) }

        coVerify { repository.getPopularShow(apiKey, region) }
        assertTrue(apiError is ApiResponse.Failure)
        assertEquals(exceptionTest.message, (apiError as ApiResponse.Failure).exception.message)

    }
}