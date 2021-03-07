package com.zygotecnologia.zygotv.main

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.zygotecnologia.zygotv.R


class MainActivity : AppCompatActivity(){



//    private val binding: ActivityMainBinding by lazy {
//        DataBindingUtil.
//    }

    private val showList: RecyclerView by lazy { findViewById(R.id.rv_show_list) }
    private val btnSearch: ImageButton by lazy { findViewById(R.id.ib_search) }
    private val searchBar: LinearLayout by lazy { findViewById(R.id.ll_search) }
    private val ivSearch: ImageView by lazy { findViewById(R.id.iv_search) }
    private val ivClose: ImageView by lazy { findViewById(R.id.iv_close_search) }
    private val tabLayout : TabLayout by lazy { findViewById(R.id.tab_layout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_main
        )

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(navController.graph.startDestination, false)
            .build()


        btnSearch.setOnClickListener {
            setupSearchBarVisibility()
        }

        ivClose.setOnClickListener {
            setupSearchBarVisibility()
        }

        tabLayout.selectTab(tabLayout.getTabAt(1))

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
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
        if (searchBar.isVisible) {
            searchBar.visibility = View.GONE
        } else {
            searchBar.visibility = View.VISIBLE
        }
    }


}