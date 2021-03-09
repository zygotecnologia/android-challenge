package com.zygotecnologia.zygotv.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.viewmodel.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val searchVm: SearchViewModel by viewModel()

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
    private val navController: NavController by lazy { navHostFragment.navController }

    private val navOptions: NavOptions by lazy {
        NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(navController.graph.startDestination, false)
                .build()
    }

    private val watcherSearch : TextWatcher by lazy {
        object :TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                if (editable?.length == 0) {
                    navController.navigate(R.id.homeSeriesFragment, null, navOptions)
                }
                searchVm.setSearchText(editable.toString())
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        )
        setupView()
    }

    private fun setupView(){
        setupListeners()
        setupTabLayout()
        binding.etSearch.addTextChangedListener(watcherSearch)
    }

    private fun setupListeners() {
        binding.ibSearch.setOnClickListener {
            setupSearchBarVisibility()
            navController.navigate(R.id.searchResultFragment, null, navOptions)
        }

        binding.ivCloseSearch.setOnClickListener {
            setupSearchBarVisibility()
        }
    }

    private fun setupTabLayout(){

        binding.tabLayout.selectTab( binding.tabLayout.getTabAt(1))

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> navController.navigate(R.id.functionalityNotAvailableFragment, null, navOptions)
                    1 -> navController.navigate(R.id.homeSeriesFragment, null, navOptions)
                    2 -> navController.navigate(R.id.functionalityNotAvailableFragment, null, navOptions)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setupSearchBarVisibility() {
        if (binding.llSearch.isVisible) {
            binding.llSearch.visibility = View.GONE
        } else {
            binding.llSearch.visibility = View.VISIBLE
            binding.etSearch.requestFocus()
        }
    }


}