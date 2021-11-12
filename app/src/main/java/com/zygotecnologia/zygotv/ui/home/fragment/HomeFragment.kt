package com.zygotecnologia.zygotv.ui.home.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.HomeFragmentBinding
import com.zygotecnologia.zygotv.extension.navigateWithAnimations
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.ui.home.adapter.CategoriesAndSeriesRecyclerAdapter
import com.zygotecnologia.zygotv.ui.home.viewmodel.HomeViewModel
import com.zygotecnologia.zygotv.utils.isConnected
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by viewModel()

    companion object {
        private const val TAG = "HOME CONNECTION NETWORK"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isConnected(view, viewLifecycleOwner, TAG, {
            observeEvents(view)
        })

        setupLayout()
    }

    private fun setupLayout() {
        binding.homeIconSearch.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.searchFragment)
        }
    }

    private fun observeEvents(view: View) {

        viewModel.listShow.observe(viewLifecycleOwner, { list ->
            setHomeRecycler(view, list)

        })
        viewModel.loadFullShow()

        viewModel.posterUrl.observe(viewLifecycleOwner, { url ->
            binding.homeTextNameSerie.text = getString(R.string.home_text_highlighted_series)
            Glide.with(this)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .into(binding.homeImageMostPopular)
        })
        viewModel.getUrlImage()
    }

    private fun setHomeRecycler(view:View, list: List<Pair<String, List<Show>>>) {
        binding.itemRecyclerSeries.run {
            adapter = CategoriesAndSeriesRecyclerAdapter(view.context, list).apply {
                onClick = { show ->
                    val directions = HomeFragmentDirections
                        .actionHomeFragmentToSeriesDetailsFragment(show)
                    isConnected(view, viewLifecycleOwner, TAG, {
                        findNavController().navigateWithAnimations(directions)
                    })
                }
            }
        }
    }
}