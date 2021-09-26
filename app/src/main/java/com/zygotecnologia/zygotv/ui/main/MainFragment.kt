package com.zygotecnologia.zygotv.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentMainBinding
import com.zygotecnologia.zygotv.ui.adapters.MainViewPagerAdapter

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configViewPager()
    }

    private fun configTabLayout(){
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            when(position){
                0 -> tab.text = getString(R.string.tab_item_films)
                1 -> tab.text = getString(R.string.tab_item_series)
                else -> tab.text = getString(R.string.tab_item_favorites)
            }

        }.attach()
    }

    private fun configViewPager(){
        //desabilitando o swipe do viewPager, e tornando a navegação possível somente pelas tabs
        binding.viewPager.also {
            it.isUserInputEnabled = false
            it.adapter = MainViewPagerAdapter(requireActivity())
            it.currentItem = 1
            it.offscreenPageLimit = 3
        }
        configTabLayout()
    }

}