package com.zygotecnologia.zygotv.main.presentation

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.zygotecnologia.zygotv.NavGraphDirections
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureAppBar()
    }

    private fun configureAppBar() {
        configureTitleStyle()
        configureHomeButton()
    }

    private fun configureTitleStyle() {
        val appName = getString(R.string.app_name)
        val appNameHighlight = getString(R.string.app_name_highlight)
        val highlightColor = ContextCompat.getColor(this, R.color.title_highlight_color)

        val highlightedTitle = SpannableString(appName)
            .highlightText(appNameHighlight, withStyle = ForegroundColorSpan(highlightColor))

        binding.appBar.title = highlightedTitle
    }

    private fun configureHomeButton() {
        val navHostFragment = getNavHostFragment()
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.appBar.setOnClickListener {
            navController.navigate(NavGraphDirections.popToHome())
        }

        binding.appBar.setNavigationOnClickListener {
            if (onBackPressedDispatcher.hasEnabledCallbacks()) onBackPressed()
            else navController.navigateUp(appBarConfiguration)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.home_fragment) binding.appBar.navigationIcon = null
            else binding.appBar.setNavigationIcon(R.drawable.ic_arrow_back_24)
        }
    }

    private fun getNavHostFragment() = supportFragmentManager
        .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
}
