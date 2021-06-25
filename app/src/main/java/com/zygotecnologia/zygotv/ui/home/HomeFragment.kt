package com.zygotecnologia.zygotv.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentHomeBinding
import com.zygotecnologia.zygotv.ui.shows.ShowsFragment

class HomeFragment : Fragment() {

    private lateinit var homePagerAdapter: HomePagerAdapter
    private lateinit var viewPager: ViewPager2

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            isSearchEnabled = false
        }
        setupObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homePagerAdapter = HomePagerAdapter(this)

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

    private fun setupObservers() {
        binding.toolbar.ivSearchIcon.setOnClickListener {
            binding.isSearchEnabled = true
        }

        binding.toolbar.searchSV.setOnQueryTextFocusChangeListener { view, b ->
            binding.isSearchEnabled = view.hasFocus() || binding.toolbar.searchSV.query.isNotEmpty()
        }

        binding.toolbar.searchSV.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val direction = HomeFragmentDirections.actionHomeFragmentToShowSearchFragment(it)
                    binding.toolbar.searchSV.findNavController().navigate(direction)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

}

class HomePagerAdapter(
    fm: Fragment
): FragmentStateAdapter(fm) {

    override fun getItemCount() = 3 // FIXME magic number

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            // TODO aba 0 (filmes) e aba 2 (favoritos)
            1 -> ShowsFragment.newInstance()
            else -> PlaceHolderFragment()
        }
    }
}

class PlaceHolderFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.placeholder_fragment, container)
    }
}