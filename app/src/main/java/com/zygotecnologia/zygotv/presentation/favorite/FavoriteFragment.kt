package com.zygotecnologia.zygotv.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentFavoriteBinding
import com.zygotecnologia.zygotv.databinding.FragmentSeasonBinding

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

}