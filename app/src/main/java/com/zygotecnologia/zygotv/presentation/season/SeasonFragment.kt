package com.zygotecnologia.zygotv.presentation.season

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.common.gone
import com.zygotecnologia.zygotv.common.load
import com.zygotecnologia.zygotv.common.showAnimate
import com.zygotecnologia.zygotv.common.showSnackBar
import com.zygotecnologia.zygotv.common.uistate.State
import com.zygotecnologia.zygotv.databinding.FragmentSeasonBinding
import com.zygotecnologia.zygotv.service.remote.data.seasons.Season
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.utils.SpacesItemDecoration
import org.koin.android.viewmodel.ext.android.viewModel


class SeasonFragment : Fragment(R.layout.fragment_season) {

    private var _binding: FragmentSeasonBinding? = null
    private val binding get() = _binding!!

    private val args: SeasonFragmentArgs by navArgs()

    private val seasonAdapter = SeasonAdapter(::clickSeason)
    private val listItems = mutableListOf<Season>()


    private fun clickSeason(positionClick: Int, status: Boolean) {
        listItems[positionClick].inVisibleDetails = status.not()
        seasonAdapter.notifyDataSetChanged()
    }


    private val viewModel by viewModel<SeasonViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeasonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadComponents()

        loadActions()

    }

    private fun loadActions() {
        viewModel.seasonLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    binding.shimmerRecyclerReason.shimmerLayout.startShimmer()
                }
                is State.Success -> {
                    binding.shimmerRecyclerReason.shimmerLayout.stopShimmer()
                    binding.shimmerRecyclerReason.shimmerLayout.gone()
                    binding.recyclerSeason.showAnimate(binding.root)
                    listItems.addAll(state.data.seasons)
                    seasonAdapter.addItems(state.data.seasons)
                }
                is State.Error -> {
                    binding.shimmerRecyclerReason.shimmerLayout.stopShimmer()
                    binding.shimmerRecyclerReason.shimmerLayout.gone()
                    binding.root.showSnackBar(
                        binding.root,
                        getString(R.string.get_series_error),
                        Snackbar.LENGTH_LONG
                    )
                }
            }

        }
    }

    private fun loadComponents() {
        binding.textoDest.text = args.myArg.name
        args.myArg.id?.let { viewModel.getSerieSeasons(it) }
        val backdrop = args.myArg.backdropPath?.let { ImageUrlBuilder.buildBackdropUrl(it) }
        binding.imageDest.load(backdrop)

        binding.recyclerSeason.apply {
            addItemDecoration(SpacesItemDecoration(top = 12, bottom = 12))
            adapter = seasonAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}