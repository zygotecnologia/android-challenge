package com.zygotecnologia.zygotv.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zygotecnologia.zygotv.presentation.favorites.FragmentFavorite
import com.zygotecnologia.zygotv.presentation.movies.FragmentMovie
import com.zygotecnologia.zygotv.presentation.series.FragmentSeries

class FragmentAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa)  {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FragmentMovie()
            1 -> FragmentSeries()
            2 -> FragmentFavorite()
            else -> FragmentMovie()
        }
    }
}