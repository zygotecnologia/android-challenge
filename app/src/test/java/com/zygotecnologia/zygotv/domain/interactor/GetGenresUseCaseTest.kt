package com.zygotecnologia.zygotv.domain.interactor

import com.zygotecnologia.zygotv.domain.model.Genre
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

class GetGenresUseCaseTest {
    @Mock
    private lateinit var repository: ZygoRepository
    private lateinit var useCase: GetGenresUseCase

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        useCase = GetGenresUseCase(repository)
    }

    @Test
    fun test() = runBlocking {

        val genre = Genre(0, "")
        val result = listOf(genre)

        fun observe() = flow {
            emit(result)
        }

        Mockito.`when`(repository.getGenres()).thenReturn(
            observe()
        )

        val testResult = useCase.execute().first()
        Assert.assertEquals(result, testResult)
    }
}