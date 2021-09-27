package com.zygotecnologia.zygotv.main

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.zygotecnologia.zygotv.series.fragments.ErrorFragment
import com.zygotecnologia.zygotv.series.fragments.SearchFragment
import com.zygotecnologia.zygotv.series.fragments.SerieFragment
import com.zygotecnologia.zygotv.series.fragments.SerieFragmentDetails
import com.zygotecnologia.zygotv.utils.testConnection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_action_bar.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        if (testConnection(this)) initTab()
        else {
            replaceFragment(ErrorFragment())
        }

    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        when (fragment) {
            is SerieFragment -> {
                bt_back_bar.visibility = View.INVISIBLE
            }
            is SearchFragment -> {
                bt_back_bar.visibility = View.VISIBLE
            }
            is SerieFragmentDetails -> {
                bt_back_bar.visibility = View.VISIBLE
            }
        }
    }

    private fun setupToolbar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true);
        supportActionBar?.setCustomView(R.layout.custom_action_bar)
        bt_back_bar.setOnClickListener {
            replaceFragment(SerieFragment())
        }

        val mRed = ForegroundColorSpan(Color.RED)
        val zygoTitle = SpannableString(getString(R.string.title))
        zygoTitle.setSpan(mRed, 4, zygoTitle.lastIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_title.text = zygoTitle
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction =
            supportFragmentManager.beginTransaction().remove(SerieFragment())
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun initTab() {
        val tabFilmes = tab_layout.newTab()
        tabFilmes.text = "Filmes"
        val tabSeries = tab_layout.newTab()
        tabSeries.text = "Series"
        val tabFavoritos = tab_layout.newTab()
        tabFavoritos.text = "Favoritos"
        tab_layout.addTab(tabFilmes)
        tab_layout.addTab(tabSeries)
        tab_layout.addTab(tabFavoritos)
        tab_layout.getTabAt(1)?.select()
        replaceFragment(SerieFragment())
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 == null) return
                when (p0.position) {
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_zygo_bar, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.search_option)
        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (testConnection(applicationContext)) replaceFragment(SearchFragment(query))
                else Toast.makeText(applicationContext, "Verifique sua conexão", Toast.LENGTH_SHORT)
                    .show()

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }

        searchView.setOnQueryTextListener(queryTextListener)
        return true
    }

}