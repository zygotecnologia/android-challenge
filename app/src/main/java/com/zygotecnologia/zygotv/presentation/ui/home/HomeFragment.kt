package com.zygotecnologia.zygotv.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zygotecnologia.zygotv.ext.toParentItem
import com.zygotecnologia.zygotv.presentation.di.Injector
import com.zygotecnologia.zygotv.presentation.ui.home.adapter.child.ChildItemListener
import com.zygotecnologia.zygotv.presentation.ui.home.adapter.parent.ParentItemAdapter
import com.zygotecnologia.zygotv.presentation.ui.home.item.child.ChildItem
import com.zygotecnologia.zygotv.presentation.ui.home.item.parent.ParentItem
import com.google.android.material.tabs.TabLayout
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.HomeFragmentBinding
import com.zygotecnologia.zygotv.presentation.ui.home.viewmodel.HomeViewModel
import com.zygotecnologia.zygotv.presentation.ui.home.viewmodel.HomeViewModelFactory
import com.zygotecnologia.zygotv.utils.Constants.internetErrorMessage
import com.zygotecnologia.zygotv.utils.Constants.premiumMessage
import com.zygotecnologia.zygotv.utils.Constants.seriesTab
import com.zygotecnologia.zygotv.utils.Constants.tabList
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var factory: HomeViewModelFactory
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, factory).get(
            HomeViewModel::class.java
        )
    }
    private lateinit var binding: HomeFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as Injector).createHomeSubComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.home_fragment,
            container,
            false
        )
        setupTabLayout()
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        with(viewModel) {
            getShows().observe(viewLifecycleOwner, { shows ->
                if (shows.isNullOrEmpty()) {
                    showEmptyView()
                    return@observe
                }
                setupAdapter(shows.toParentItem())
            })
        }
    }

    private fun showEmptyView() {
        binding.emptyView.visibility = View.VISIBLE
        binding.tvEmptyView.text = internetErrorMessage
    }

    private fun setupAdapter(list: List<ParentItem>) {
        val adapter = ParentItemAdapter(object : ChildItemListener {
            override fun onClick(item: ChildItem) {
                defineClickListener(item)
            }
        }).apply { data = list }
        binding.parentRecyclerview.adapter = adapter
        binding.parentRecyclerview.layoutManager = LinearLayoutManager(context)
    }

    private fun defineClickListener(item: ChildItem) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        action.tvShowId = item.id
        findNavController().navigate(action)
    }

    private fun setupTabLayout() {
        binding.run {
            with(tabLayout) {
                tabList.forEach { label ->
                    addTab(newTab().apply { text = label })
                }
                tabGravity = TabLayout.GRAVITY_FILL
                getTabAt(seriesTab)?.select()
                addOnTabSelectedListener(tabLayoutListener)
            }
        }
    }

    private val tabLayoutListener: TabLayout.OnTabSelectedListener =
        object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    seriesTab -> {
                        binding.emptyView.visibility = View.GONE
                    }
                    else -> {
                        binding.emptyView.visibility = View.VISIBLE
                        binding.tvEmptyView.text = premiumMessage
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        }
}

