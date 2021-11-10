package com.zygotecnologia.zygotv.ui.search.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zygotecnologia.zygotv.databinding.SearchFragmentBinding
import com.zygotecnologia.zygotv.ui.search.viewmodel.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private lateinit var binding: SearchFragmentBinding
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}