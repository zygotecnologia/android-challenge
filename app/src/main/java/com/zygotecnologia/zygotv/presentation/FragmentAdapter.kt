package com.zygotecnologia.zygotv.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zygotecnologia.zygotv.presentation.favorite.FavoriteFragment
import com.zygotecnologia.zygotv.presentation.movie.MovieFragment
import com.zygotecnologia.zygotv.presentation.series.SeriesFragment

class FragmentAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment()
            2 -> FavoriteFragment()
            else -> SeriesFragment()
        }
    }
}