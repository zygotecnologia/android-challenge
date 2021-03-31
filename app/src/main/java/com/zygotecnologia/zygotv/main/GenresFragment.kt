package com.zygotecnologia.zygotv.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenresFragment : Fragment(), OnClickShowAndGenre {

    internal lateinit var callback: FragmentListener

    private val viewModel: DashboardViewModel by viewModels()

    private lateinit var rvGenreAndShows: RecyclerView
    private lateinit var imgPosterShow: ImageView
    private lateinit var tvTitleShow: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shows_per_genre, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
        viewModel.loadGenres()
        viewModel.loadListOfShows()
        callback.showLoading(true)
    }

    private fun setupView() {
        view?.let {
            rvGenreAndShows = it.findViewById(R.id.rv_show_genre_and_show)
            imgPosterShow = it.findViewById(R.id.img_show_seleted_show)
            tvTitleShow = it.findViewById(R.id.tv_title_selected_show)
        }
    }

    private fun setupObservers() {
        viewModel.mutableGenreAndShowList.observe(requireActivity(), { genreAndShowList->
            genreAndShowList?.let {
                val listOfGenreAndShowFiltered = it.filter {
                    it.listShowDetails!!.isNotEmpty()
                }
                    rvGenreAndShows.adapter = GenreAndShowAdapter(listOfGenreAndShowFiltered, this)
            }
            callback.showLoading(false)
        })

        viewModel.mutableError.observe(requireActivity(), {
            callback.showError()
        })

        viewModel.mutableListOfShowDetails.observe(requireActivity(), {
            val mostPopularShowDetails: Show? = mostPopularShow(it)
            Glide.with(this)
                .load(mostPopularShowDetails?.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.img_most_popular_show_custom))
                .into(imgPosterShow)
            tvTitleShow.text = mostPopularShowDetails?.name
        })
    }

    private fun mostPopularShow(it: List<Show>): Show? {
        var popularityRate = 0.00
        var mostPopularShowDetails: Show? = null
        it.forEach {
            if (it.popularity > popularityRate) {
                popularityRate = popularityRate
                mostPopularShowDetails = it
            }
        }
        return mostPopularShowDetails
    }

    companion object {
        @JvmStatic
        fun newInstance() = GenresFragment()
    }

    override fun onShowClick(show: Show) {
        Toast.makeText(requireContext(), show.id.toString(), Toast.LENGTH_LONG).show()
        callback.nextFragment(ShowFragment.newInstance(show))
    }
}

interface FragmentListener {
    fun nextFragment(fragment: Fragment)
    fun showLoading(boolean: Boolean)
    fun showError()
}