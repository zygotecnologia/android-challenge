package com.zygotecnologia.zygotv.series.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentSerieBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.SeriesRepository
import com.zygotecnologia.zygotv.series.viewmodel.SeriesViewModel
import com.zygotecnologia.zygotv.series.adapter.GenreAndSeriesAdapter
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.coroutines.CoroutineContext

class SerieFragment : Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO
    var _binding: FragmentSerieBinding? = null
    val binding: FragmentSerieBinding get() = _binding!!
    private var mostPopular: Show? = null

    private val mViewModel: SeriesViewModel by viewModel {
        parametersOf(SeriesRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSerieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModelObservers()

    }

    private fun viewModelObservers() {
        mViewModel.dataListGenres.observe(viewLifecycleOwner, Observer {
            binding.rvGenres.adapter = GenreAndSeriesAdapter(it)
        })
        mViewModel.dataListPopular.observe(viewLifecycleOwner, {
            mostPopular(it)
        })
        mViewModel.liveLoading.observe(viewLifecycleOwner, {
            if(!it){
                binding.pbMain.visibility = GONE
                binding.rvGenres.visibility = VISIBLE
            } else{
                binding.pbMain.visibility = VISIBLE
                binding.rvGenres.visibility = GONE
            }
        })
    }

    private fun mostPopular(list: List<Show>?) {
        var rate = 0f
        list?.map {
            if (it.popularity > rate) {
                rate = it.popularity
                mostPopular = it
            }
        }
        Glide.with(this)
            .load(mostPopular?.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .into(binding.ivMostPopular)
        binding.tvTitleMostPopular.text = mostPopular?.name
    }

    private fun initView() {
        binding.ivMostPopular.setOnClickListener {
            replaceFragment(SerieFragmentDetails(mostPopular!!))
        }
        binding.rvGenres.layoutManager =
            LinearLayoutManager(context)
        mViewModel.loadShows()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction =
            fragmentManager?.beginTransaction()!!.remove(SerieFragment())
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}