package com.zygotecnologia.zygotv.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentHomeBinding
import com.zygotecnologia.zygotv.view.shows.ShowsFragment

class HomeFragment : Fragment() {

    private lateinit var homePagerAdapter: HomePagerAdapter
    private lateinit var viewPager: ViewPager2

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply { isSearchEnabled = false }
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
                MOVIES_TAB_POS -> getString(R.string.tab1)
                SHOWS_TAB_POS -> getString(R.string.tab2)
                FAVORITES_TAB_POS -> getString(R.string.tab3)
                else -> getString(R.string.tab3)
            }
        }.attach()
        tabLayout.getTabAt(SHOWS_TAB_POS)?.select()
    }

    private fun setupObservers() {
        binding.toolbar.ivSearchIcon.setOnClickListener {
            binding.isSearchEnabled = true
        }

        binding.toolbar.svSearchShow.setOnQueryTextFocusChangeListener { view, _ ->
            binding.isSearchEnabled = view.hasFocus() || binding.toolbar.svSearchShow.query.isNotEmpty()
        }

        binding.toolbar.svSearchShow.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {

                    binding.toolbar.svSearchShow.apply {
                        setQuery("", false)
                        clearFocus()
                    }

                    goToSearchResults(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun goToSearchResults(it: String) {
        val direction = HomeFragmentDirections.actionHomeFragmentToShowSearchFragment(it)
        findNavController().navigate(direction)
    }

    companion object {
        private const val MOVIES_TAB_POS = 0
        private const val SHOWS_TAB_POS = 1
        private const val FAVORITES_TAB_POS = 2
    }
}

class HomePagerAdapter(
    fm: Fragment
): FragmentStateAdapter(fm) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
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
        return inflater.inflate(R.layout.fragment_placeholder, container)
    }
}