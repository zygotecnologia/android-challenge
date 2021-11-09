package com.zygotecnologia.zygotv.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.api.TmdbApi
import com.zygotecnologia.zygotv.data.model.GenreResponse
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.data.model.ShowResponse
import com.zygotecnologia.zygotv.data.repository.ShowsRepository
import com.zygotecnologia.zygotv.databinding.FragmentHomeBinding
import com.zygotecnologia.zygotv.network.Resource
import com.zygotecnologia.zygotv.ui.adapter.HomeAdapter
import com.zygotecnologia.zygotv.ui.adapter.MostPopularAdapter
import com.zygotecnologia.zygotv.ui.base.BaseFragment
import com.zygotecnologia.zygotv.ui.viewmodel.HomeViewModel
import com.zygotecnologia.zygotv.utils.ShowByGenreFactory

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, ShowsRepository>() {

    private lateinit var navigation: NavController

    private val homeAdapter by lazy { HomeAdapter(::onClickItem) }
    private val mostPopularAdapter by lazy { MostPopularAdapter() }
    private val showsFactory by lazy { ShowByGenreFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        viewModel.getGenres()
        viewModel.getShows()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.genres.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> onGenreSuccess(it.value)
                is Resource.Loading -> onLoading()
                is Resource.Failure -> onFailure()
            }
        })
    }

    private fun onGenreSuccess(genreResponse: GenreResponse) {
        viewModel.shows.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> onShowSuccess(it.value, genreResponse)
                is Resource.Loading -> onLoading()
                is Resource.Failure -> onFailure()
            }
        })
    }

    private fun onShowSuccess(showResponse: ShowResponse, genreResponse: GenreResponse) {
        val showsList = showsFactory
            .generateListOfShowsByGenre(
                genreResponse.genres,
                showResponse.results
            )
        homeAdapter.updateShowList(showsList)
        mostPopularAdapter.updateMostPopularShow(showResponse.results[0])
    }

    private fun setupViews() {

        bindig.rvShowList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }
        bindig.mostPopular.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mostPopularAdapter
        }
    }

    private fun onLoading() {
    }

    private fun onFailure() {
        Toast.makeText(
            requireContext(),
            "Algo deu errado, verifique a sua conexão e tente novamente",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ShowsRepository {
        val api = remoteDataSource.getInstance(TmdbApi::class.java)
        return ShowsRepository(api)
    }

    private fun onClickItem(show: Show) {
        val bundle = Bundle()
        show.let {
            bundle.putInt("ShowId", it.id!!)
            bundle.putString("ShowName", it.name!!)
            bundle.putString("ShowPosterPath", it.posterPath)
        }

        navigation = Navigation.findNavController(this.requireActivity(), R.id.home_fragment)
        navigation.navigate(R.id.go_to_show_details, bundle)
    }
}