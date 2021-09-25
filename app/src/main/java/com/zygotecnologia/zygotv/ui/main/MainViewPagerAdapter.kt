package com.zygotecnologia.zygotv.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zygotecnologia.zygotv.ui.favorites.FavoritesFragment
import com.zygotecnologia.zygotv.ui.films.FilmsFragment
import com.zygotecnologia.zygotv.ui.series.SeriesFragment

class MainActivityAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FilmsFragment()
            1 -> SeriesFragment()
            else -> FavoritesFragment()
        }
    }

    override fun getItemCount(): Int = 3
}