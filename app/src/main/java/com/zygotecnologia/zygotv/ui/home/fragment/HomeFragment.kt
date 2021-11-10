package com.zygotecnologia.zygotv.ui.home.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.HomeFragmentBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.navigateWithAnimations
import com.zygotecnologia.zygotv.ui.home.adapter.CategoriesAndSeriesRecyclerAdapter
import com.zygotecnologia.zygotv.ui.home.viewmodel.HomeViewModel
import com.zygotecnologia.zygotv.utils.ConnectionLiveData
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private val listAdapter: MutableList<Pair<String, List<Show>>> = mutableListOf()
    private val viewModel: HomeViewModel by viewModel()

    companion object {
        const val TAG = "HOME CONNECTION NETWORK"
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
        val connectionLiveData = ConnectionLiveData(view.context)

        connectionLiveData.observe(viewLifecycleOwner, { isNetworkAvailable ->
            when (isNetworkAvailable) {
                true -> {
                    Log.i(TAG, "Internet ON")
                        observeEvents(view.context)
                }
                false -> {
                    Log.i(TAG, "Internet OFF")
                    val snackbar =
                        Snackbar.make(binding.root, "Sem Internet", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
            }
        })
    }

    private fun observeEvents(context: Context) {
        viewModel.listActionAventure.observe(viewLifecycleOwner, { listActionAventure ->
            val list = Pair("Ação e Aventura", listActionAventure)
            setHomeRecycler(context, list)
        })
        viewModel.loadActionAventure()

        viewModel.listComedy.observe(viewLifecycleOwner, { listComedy ->
            val list = Pair("Comédia", listComedy)
            setHomeRecycler(context, list)
        })
        viewModel.loadComedy()

        viewModel.listDrama.observe(viewLifecycleOwner, { listDrama ->
            val list = Pair("Drama", listDrama)
            setHomeRecycler(context, list)
        })
        viewModel.loadDrama()

        viewModel.listFamily.observe(viewLifecycleOwner, { listAnimation ->
            val list = Pair("Familia", listAnimation)
            setHomeRecycler(context, list)
        })
        viewModel.loadFamily()

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

    private fun setHomeRecycler(context: Context, list: Pair<String, List<Show>>) {
        if (!listAdapter.contains(list)) {
            listAdapter.add(list)
        }

        binding.itemRecyclerSeries.run {
            adapter = CategoriesAndSeriesRecyclerAdapter(context, listAdapter).apply {
                onClick = { show ->
                    val directions = HomeFragmentDirections
                        .actionHomeFragmentToSeriesDetailsFragment(show)
                    findNavController().navigateWithAnimations(directions)
                }
            }
        }
    }
}