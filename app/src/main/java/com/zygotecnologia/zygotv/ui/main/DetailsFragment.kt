package com.zygotecnologia.zygotv.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.model.Season
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.databinding.FragmentDetailsBinding
import com.zygotecnologia.zygotv.ui.MainViewModel
import com.zygotecnologia.zygotv.ui.adapters.SeasonAdapter
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val viewModel: MainViewModel by viewModel()

    private var showId : String?  = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showId = arguments?.getString("Id")

        initUi()

        initObservers()

        loadShows()
    }

    private fun initUi(){
        binding.includeToolbar.ivArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservers(){

        viewModel.show.observe(viewLifecycleOwner, ::configImageBanner)
        viewModel.seasons.observe(viewLifecycleOwner, ::onGetSeasons)

        binding.executePendingBindings()
    }

    private fun loadShows(){
        showId?.let{
            viewModel.getShow(it.toInt())
        }
    }

    private fun configImageBanner(show: Show?){

        binding.show = show

        Glide.with(binding.ivShowPopular)
            .load(show?.backdropPath?.let { ImageUrlBuilder.buildBackdropUrl(it) })
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .into(binding.ivShowPopular)
    }

    private fun onGetSeasons(seasonList: MutableList<Season>){
        val show = viewModel.show.value
        val showSeasonSize = show?.numberOfSeasons ?: 1
        if(showSeasonSize == seasonList.size){
            configRecyclerViewSeasons(seasonList)
        }
    }

    private fun configRecyclerViewSeasons(seasonList: MutableList<Season>){
        val list = seasonList.sortedBy { it.seasonNumber }.toMutableList()

        binding.rvSeasonList.also{
            it.adapter = SeasonAdapter(list)
        }
    }
}