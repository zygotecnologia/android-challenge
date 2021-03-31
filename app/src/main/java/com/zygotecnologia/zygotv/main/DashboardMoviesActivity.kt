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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.zygotecnologia.zygotv.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardMoviesActivity : AppCompatActivity(), FragmentListener {

    private val appTitleTextView: TextView by lazy { findViewById(R.id.tv_title_menu) }
    private val imgBackButtonView: ImageView by lazy { findViewById(R.id.bt_back_button_menu) }
    private val tvTabMovies: TextView by lazy { findViewById(R.id.tv_movies) }
    private val tvSeries: TextView by lazy { findViewById(R.id.tv_series_dash_movies) }
    private val tvFavorites: TextView by lazy { findViewById(R.id.tv_fav_dash_movies) }
    private val linearTab: LinearLayout by lazy { findViewById(R.id.ll_tabs_dash_movies) }
    private val pbMovies: ProgressBar by lazy { findViewById(R.id.pb_dash_movies) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_movies)
        setupToolbar()
        setupView()
        setListeners()
    }

    private fun setupToolbar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true);
        supportActionBar?.setCustomView(R.layout.custom_action_bar)

        imgBackButtonView.setOnClickListener {
            fragmentChange(GenresFragment.newInstance())
        }
    }

    private fun setListeners() {
        imgBackButtonView.setOnClickListener {
            fragmentChange(GenresFragment.newInstance())
        }
        tvTabMovies.setOnClickListener {
            tvTabMovies.setTextColor(resources.getColor(R.color.seleted_tab_item))
            tvSeries.setTextColor(resources.getColor(R.color.not_seleted_tab_item))
            tvFavorites.setTextColor(resources.getColor(R.color.not_seleted_tab_item))
        }
        tvSeries.setOnClickListener {
            tvSeries.setTextColor(resources.getColor(R.color.seleted_tab_item))
            tvTabMovies.setTextColor(resources.getColor(R.color.not_seleted_tab_item))
            tvFavorites.setTextColor(resources.getColor(R.color.not_seleted_tab_item))
        }

        tvFavorites.setOnClickListener {
            tvFavorites.setTextColor(resources.getColor(R.color.seleted_tab_item))
            tvSeries.setTextColor(resources.getColor(R.color.not_seleted_tab_item))
            tvTabMovies.setTextColor(resources.getColor(R.color.not_seleted_tab_item))
        }

    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is GenresFragment) {
            fragment.callback = this
        }
        if (fragment is ShowFragment) {
            fragment.callback = this
            imgBackButtonView.visibility = View.VISIBLE
        }
        if (fragment is SearchFragment) {
            fragment.callback = this
            hideView()
        } else {
            showView()
        }
    }

    private fun showView() {
        linearTab.visibility = View.VISIBLE
    }

    private fun hideView() {
        linearTab.visibility = View.GONE
    }

    private fun setupView() {
        val title = SpannableString(getString(R.string.app_name))
        val color = ForegroundColorSpan(Color.WHITE)
        title.setSpan(color, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        appTitleTextView.text = title

        fragmentChange(GenresFragment.newInstance())
    }

    private fun filterShow(query: String?) {
        fragmentChange(SearchFragment.newInstance(query))
    }

    private fun fragmentChange(fragment: Fragment) {
        val manager = this.supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container_main_view_shows, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun nextFragment(fragment: Fragment) {
        fragmentChange(fragment)
    }

    override fun showLoading(exibir: Boolean) {
        pbMovies.visibility = (if (exibir) View.VISIBLE else View.GONE)
    }

    override fun showError() {
        if (pbMovies.isVisible) {
            pbMovies.visibility = View.GONE
        }
        fragmentChange(ErrorFragment.newInstance())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_zygo_tv, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterShow(query)
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