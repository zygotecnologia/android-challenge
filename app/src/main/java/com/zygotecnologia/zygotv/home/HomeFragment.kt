package com.zygotecnologia.zygotv.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zygotecnologia.zygotv.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureList()
    }

    private fun configureList() {
        val adapter = ShowsAdapter()

        binding.rvShowList.adapter = adapter

        viewModel.shows.observe(viewLifecycleOwner) {
            adapter.updateShows(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}