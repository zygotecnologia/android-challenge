package com.zygotecnologia.zygotv.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.data.api.TmdbApi
import com.zygotecnologia.zygotv.data.model.ShowDetailsModel.ShowDetailsResponse
import com.zygotecnologia.zygotv.data.repository.ShowsRepository
import com.zygotecnologia.zygotv.databinding.FragmentShowDetailsBinding
import com.zygotecnologia.zygotv.network.Resource
import com.zygotecnologia.zygotv.ui.adapter.ShowSeasonsAdapter
import com.zygotecnologia.zygotv.ui.base.BaseFragment
import com.zygotecnologia.zygotv.ui.viewmodel.ShowDetailsViewModel
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ShowDetailsFragment :
    BaseFragment<ShowDetailsViewModel, FragmentShowDetailsBinding, ShowsRepository>() {

    private val seasonAdapter by lazy { ShowSeasonsAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val (showId, showName, showPosterPath) = getBundles()

        viewModel.getShowDetails(showId)
        setupObservers()
        setupViews(showName, showPosterPath)
    }

    private fun getBundles(): Triple<Int, String, String> {
        var showId = 0
        var showName = ""
        var showPosterPath = ""
        arguments?.let {
            showId = it.getInt("ShowId")
            showName = it.getString("ShowName")!!
            showPosterPath = it.getString("ShowPosterPath")!!

        }
        return Triple(showId, showName, showPosterPath)
    }

    private fun setupObservers() {
        viewModel.showDetail.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> onDetailsSuccess(it.value)
                is Resource.Loading -> onLoading()
                is Resource.Failure -> onFailure()
            }
        })
    }

    private fun onDetailsSuccess(details: ShowDetailsResponse) {
        seasonAdapter.updateSeasonList(details.seasons)
    }

    private fun setupViews(showName: String, showPosterPath: String) {
        bindig.seasonList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = seasonAdapter
        }
        bindig.detailShowName.text = showName
        Glide.with(bindig.featuredShowPoster)
            .load(showPosterPath.let { ImageUrlBuilder.buildBackdropUrl(it) })
            .apply(RequestOptions().centerCrop())
            .into(bindig.featuredShowPoster)
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

    override fun getViewModel() = ShowDetailsViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShowDetailsBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ShowsRepository {
        val api = remoteDataSource.getInstance(TmdbApi::class.java)
        return ShowsRepository(api)
    }
}