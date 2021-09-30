package com.zygotecnologia.zygotv.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.DetailFragmentBinding
import com.zygotecnologia.zygotv.ext.loadWithURL
import com.zygotecnologia.zygotv.presentation.di.Injector
import com.zygotecnologia.zygotv.presentation.ui.detail.viewmodel.DetailViewModel
import com.zygotecnologia.zygotv.presentation.ui.detail.viewmodel.DetailViewModelFactory
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import javax.inject.Inject

class DetailFragment : Fragment() {
    @Inject
    lateinit var factory: DetailViewModelFactory
    private lateinit var viewModel: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: DetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as Injector).createDetailSubComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        setupActions()
        return binding.root
    }

    private fun setupActions() {
        if(args.tvShowId != 0){
            viewModel.getShowById(args.tvShowId).observe(viewLifecycleOwner, { item ->
                with(binding){
                    imgFavoriteItem.loadWithURL(
                        ImageUrlBuilder.buildBackdropUrl(item.backdropPath ?: ""),
                        RequestOptions()
                            .placeholder(R.drawable.ic_loading)
                            .error(R.drawable.ic_loading)
                    )
                    tvDetailFragmentTitle.text = item.originalName
                    tvDetailFragmentDescription.text = item.overview
                }
            })
        }
    }
}
