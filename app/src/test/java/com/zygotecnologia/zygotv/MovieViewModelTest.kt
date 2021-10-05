package com.zygotecnologia.zygotv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.zygotecnologia.zygotv.domain.model.ApiResult
import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.domain.repository.MoviesRepository
import com.zygotecnologia.zygotv.presentation.movies.MovieViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieListObserver: Observer<List<Show>>

    @Mock
    private lateinit var observerMovieError: Observer<String>

    private lateinit var viewModel: MovieViewModel

    @Test
    fun `when viewModel getMovie result in success then sets movieListLiveData`() {
        //Arrange
        val list = listOf(
                Show(
                     genres = listOf<Genre>(Genre(
                         id = 1,
                         name = ""
                     )),
                    originalName = "",
                    genreIds = listOf<Int>(
                    ),
                    name = "",
                    voteCount = 1,
                    backdropPath = "",
                    originalLanguage = "",
                    id = 2,
                    overview  = "",
                    posterPath = ""
                )
        )

        val resultSuccess = MockRepository(ApiResult.Success(list))
        viewModel = MovieViewModel(resultSuccess)
        viewModel.movieList.observeForever(movieListObserver)

        //Act
        viewModel.getMovies()

        // Assert
        verify(movieListObserver).onChanged(list )

    }
}

class MockRepository(private val result: ApiResult) :
    MoviesRepository {
    override suspend fun getMovies(usersResultCallback: (result: ApiResult) -> Unit) {
        usersResultCallback(result)
    }
}

