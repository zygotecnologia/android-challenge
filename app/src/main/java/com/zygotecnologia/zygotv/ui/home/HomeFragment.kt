package com.zygotecnologia.zygotv.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.ui.shows.ShowsFragment

class HomeFragment : Fragment() {

    private lateinit var homePagerAdapter: HomePagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homePagerAdapter = HomePagerAdapter(parentFragmentManager, lifecycle)
        viewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = homePagerAdapter
        viewPager.isSaveEnabled = true

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "FILMES" // TODO strings.xml
                1 -> "SÉRIES"
                else -> "FAVORITOS"
            }
        }.attach()
        tabLayout.getTabAt(1)?.select()
    }
}

class HomePagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount() = 3 // FIXME magic number

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            // TODO aba 0 (filmes) e aba 2 (favoritos)
            1 -> ShowsFragment.newInstance()
            else -> PlaceHolderFragment()
        }
    }
}

class PlaceHolderFragment: Fragment()