package com.zygotecnologia.zygotv.domain.interactor

import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.domain.repository.ZygoRepository
import junit.framework.Assert
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetPopularShowUseCaseTest {
    @Mock
    private lateinit var repository: ZygoRepository
    private lateinit var useCase: GetPopularShowsUseCase

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        useCase = GetPopularShowsUseCase(repository)
    }

    @Test
    fun test() = runBlocking {
        val param = emptyList<Genre>()
        val shows = Show(param, null, null, null,
            null, null, null, null, null, null)
        val result = listOf(shows)

        fun observe() = flow {
            emit(result)
        }

        Mockito.`when`(repository.getPopularShows(param)).thenReturn(
            observe()
        )

        val testResult = useCase.execute(param).first()
        Assert.assertEquals(result, testResult)
    }
}