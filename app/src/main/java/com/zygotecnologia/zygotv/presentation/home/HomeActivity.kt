package com.zygotecnologia.zygotv.presentation.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.tabs.TabLayout
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.common.colorize
import com.zygotecnologia.zygotv.utils.OnTabSelected


class HomeActivity : AppCompatActivity() {

    private val navController: NavController by lazy { findNavController(R.id.nav_host) }
    private var menuItem: MenuItem? = null
    private lateinit var tabItems: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.default_toolbar)
        val toolbarTitle: TextView = findViewById(R.id.title_toolbar)
        tabItems = findViewById(R.id.tablayout)

        loadComponents(toolbar, toolbarTitle)
        loadActions()

    }

    private fun loadActions() {
        tabItems.selectTab(tabItems.getTabAt(1))

        tabItems.addOnTabSelectedListener(object : OnTabSelected {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> navController.navigate(R.id.movieFragment, null, navOptions)
                    1 -> navController.navigate(R.id.seriesFragment, null, navOptions)
                    2 -> navController.navigate(R.id.favoriteFragment, null, navOptions)
                }
            }

        })
    }

    private fun loadComponents(
        toolbar: Toolbar,
        toolbarTitle: TextView
    ) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupWithNavController(toolbar, navController)


        toolbarTitle.colorize(getString(R.string.home_tv_toolbar), R.color.toolbar_title_color)
    }

    private val onDestinationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            menuItem?.isVisible = destination.id == R.id.seriesFragment

            when (destination.id) {
                R.id.seriesFragment -> tabItems.selectTab(tabItems.getTabAt(1))

                R.id.favoriteFragment -> tabItems.selectTab(tabItems.getTabAt(2))

                R.id.movieFragment -> tabItems.selectTab(tabItems.getTabAt(0))
            }
        }

    private val navOptions: NavOptions by lazy {
        NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(navController.graph.startDestination, false)
            .build()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        menuItem = menu?.getItem(0)
        return true
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(onDestinationListener)
    }

    override fun onStop() {
        super.onStop()
        navController.removeOnDestinationChangedListener(onDestinationListener)
    }


}