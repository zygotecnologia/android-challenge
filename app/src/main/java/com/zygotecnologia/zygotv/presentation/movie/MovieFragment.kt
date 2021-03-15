package com.zygotecnologia.zygotv.presentation.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentFavoriteBinding
import com.zygotecnologia.zygotv.databinding.FragmentMovieBinding


class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }


}