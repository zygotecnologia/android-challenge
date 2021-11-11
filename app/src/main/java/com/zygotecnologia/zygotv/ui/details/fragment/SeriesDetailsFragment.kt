package com.zygotecnologia.zygotv.ui.details.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.SeriesDetailsFragmentBinding
import com.zygotecnologia.zygotv.extension.navigateWithAnimations
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.ui.details.adapter.SeasonRecyclerAdapter
import com.zygotecnologia.zygotv.ui.details.viewmodel.SeriesDetailsViewModel
import com.zygotecnologia.zygotv.ui.home.fragment.HomeFragment
import com.zygotecnologia.zygotv.utils.testConnection
import org.koin.android.viewmodel.ext.android.viewModel

class SeriesDetailsFragment : Fragment() {

    private lateinit var binding: SeriesDetailsFragmentBinding

    private val viewModel: SeriesDetailsViewModel by viewModel()
    private val args: SeriesDetailsFragmentArgs by navArgs()

    companion object {
        private const val TAG = "DETAILS CONNECTION NETWORK"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SeriesDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testConnection(view, viewLifecycleOwner,
            isConnection = {
                Log.i(TAG, "Internet ON")
                observeEvents(view.context)
            }, notConnection = {
                Log.i(TAG, "Internet OFF")
                val snackbar = Snackbar.make(binding.root, "Sem Internet", Snackbar.LENGTH_SHORT)
                snackbar.show()
            })

        setupLayout()
    }

    private fun setupLayout() {
        binding.detailsIconBack.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.homeFragment)
        }
    }

    private fun observeEvents(context: Context) {

        viewModel.posterUrl.observe(viewLifecycleOwner, { url ->
            Glide.with(this)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .into(binding.detailsImageMostPopular)
        })
        binding.detailsTextNameSerie.text = args.show.name
        viewModel.backDropUrl(args.show)

        val id = args.show.id

        viewModel.detailsShow.observe(viewLifecycleOwner, { showDetails ->
            setHomeRecycler(context, showDetails)
        })
        viewModel.loadDetailsShow(id!!)
    }

    private fun setHomeRecycler(context: Context, showDetails: ShowDetails) {
        binding.itemDetailsRecyclerSeasons.run {
            adapter = SeasonRecyclerAdapter(context, showDetails)
        }
    }

}