package com.zygotecnologia.zygotv.presentation.home

import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.common.colorize
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.presentation.FragmentAdapter
import com.zygotecnologia.zygotv.uistate.State
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private val navController: NavController by lazy { findNavController(R.id.nav_host) }

    private val navOptions: NavOptions by lazy {
        NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(navController.graph.startDestination, false)
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar : Toolbar = findViewById(R.id.default_toolbar)
        val toolbarTitle : TextView = findViewById(R.id.title_toolbar)
        val tabLayout : TabLayout = findViewById(R.id.tablayout)

        setSupportActionBar(toolbar)

        toolbarTitle.colorize("TV",android.R.color.holo_red_light)

        tabLayout.selectTab( tabLayout.getTabAt(1))

        tabLayout.addOnTabSelectedListener(object : OnTabSelected {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> navController.navigate(R.id.movieFragment, null, navOptions)
                    1 -> navController.navigate(R.id.seriesFragment, null, navOptions)
                    2 -> navController.navigate(R.id.favoriteFragment, null, navOptions)
                }
            }

        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        return true
    }
}