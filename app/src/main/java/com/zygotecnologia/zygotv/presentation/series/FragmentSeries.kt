package com.zygotecnologia.zygotv.presentation.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.data.network.TmdbClient
import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.presentation.adapter.MovieAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentSeries : Fragment() {

    private val tmdbApi = TmdbClient.getInstance()

    private val showList: RecyclerView by lazy { view?.findViewById(R.id.rv_show_list)!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(
            R.layout.fragment_movies,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) { loadShows() }
    }

    private suspend fun loadShows() {
        val genres =
            tmdbApi
                .fetchGenresAsync(TmdbApi.TMDB_API_KEY, "BR")
                ?.genres
                ?: emptyList()
        val shows =
            tmdbApi
                .fetchPopularShowsAsync(TmdbApi.TMDB_API_KEY, "BR")
                ?.results
                ?.map { show ->
                    show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
                }
                ?: emptyList()


        withContext(Dispatchers.Main) {
            val genre2s: MutableList<Genre> = ArrayList()

            genres.forEach {
                val genre = Genre(it.id, it.name)

                val moviesGenre: MutableList<Show> = ArrayList()
                shows.forEach { Movie ->
                    if (Movie.genreIds?.contains(it.id) == true) {
                        moviesGenre.add(Movie)

                    }
                }
                if (moviesGenre.size > 0) {
                    genre.movies = moviesGenre
                    genre2s.add(genre)
                }
                showList.adapter = MovieAdapter(genre2s)
            }
        }
    }
}