package com.zygotecnologia.zygotv.presentation.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.data.network.TmdbClient
import com.zygotecnologia.zygotv.data.repository.MoviesRepositoryImpl
import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.presentation.activity.DetailActivity
import com.zygotecnologia.zygotv.presentation.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMovie : Fragment() {

    private val tmdbApi = TmdbClient.getInstance()

    private val showList: RecyclerView by lazy { view?.findViewById(R.id.rv_show_list)!! }
    private lateinit var imgView:ImageView
    private val viewModel: MovieViewModel =
        MovieViewModel.ViewModelFactory(MoviesRepositoryImpl()).create(MovieViewModel::class.java)

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
        binding()
        //  clickListener()
    }

    private suspend fun loadShows() {
        val genres =
            tmdbApi
                .fetchGenresMovieAsync(TmdbApi.TMDB_API_KEY, "BR")
                ?.genres
                ?: emptyList()
        val shows =
            tmdbApi
                .fetchMovieAsync(TmdbApi.TMDB_API_KEY, "BR")
                ?.results
                ?.map { show ->
                    show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
                }
                ?: emptyList()


        withContext(Dispatchers.Main) {

            val genre2s : MutableList<Genre> = ArrayList()

            genres.forEach {
                val genre = Genre(it.id, it.name)
                /*
                val shows : MutableList<Show> = ArrayList()
                for (a in 0..19){
                    val show = Show(listOf(),"", listOf(),"",1,"","",R.drawable.spotify,"","https://image.tmdb.org/t/p/original/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg")
                    shows.add(show)
                }
                genre.movies = shows
                 */
                val moviesGenre : MutableList<Show> = ArrayList()
                shows.forEach { Movie ->
                    if(Movie.genreIds?.contains(it.id) == true){
                        moviesGenre.add(Movie)

                    }
                }
                if(moviesGenre.size>0){
                    genre.movies = moviesGenre
                    genre2s.add(genre)
                }
            }

            showList.adapter = MovieAdapter(genre2s)
        }
    }



    fun binding(){
        imgView = view?.findViewById(R.id.img_serie)!!
    }

//    fun clickListener(){
//        imgView.setOnClickListener{ movie->
//            handleClick(movie.)
//
//        }
//    }

    private fun setObservers() {

        viewModel.movieList.observe(viewLifecycleOwner, Observer { movieListResponse ->
//            showList.adapter = movieListResponse?.let { list ->
//                MovieAdapter(list, clickListener = {
//                    handleClick(it)
//                })
//
//            }

            viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            })

            viewModel.loadingEvent.observe(viewLifecycleOwner, Observer { isVisible ->
                // loading.setVisible(false)
            })
        })
    }
    private fun handleClick(movie: Show) {
        val intent = Intent(context, DetailActivity::class.java)
        if (movie != null) {
            intent.putExtra("originalName", movie.originalName)
            intent.putExtra("poster", movie.posterPath)
            intent.putExtra("name", movie.name)
            intent.putExtra("backdropPath", movie.backdropPath)
            intent.putExtra("genre", movie.overview)
        }
        startActivity(intent)
    }
}