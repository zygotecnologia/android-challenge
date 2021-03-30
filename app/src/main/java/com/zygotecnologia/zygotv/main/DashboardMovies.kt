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
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.ShowsSearch
import com.zygotecnologia.zygotv.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardMovies : AppCompatActivity(), FragmentListener {

    private val viewModel: DashboardViewModel by viewModels()

    private val appTitleTextView: TextView by lazy { findViewById(R.id.tv_title_menu) }
    private val imgBackButtonView: ImageView by lazy { findViewById(R.id.bt_back_button_menu) }
    lateinit var linearTab: LinearLayout
    private val imgPosterShow: ImageView by lazy { findViewById(R.id.img_show) }
    private val tvTitleShow: TextView by lazy { findViewById(R.id.tt_popular_show_name) }
    private lateinit var showSearch: ShowsSearch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        viewModel.loadShow()
        setupView()
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        viewModel.mutableListOfShow.observe(this, { showList->
            showSearch = ShowsSearch(showList)
        })
    }

    private fun setupToolbar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true);
        supportActionBar?.setCustomView(R.layout.custom_action_bar)

        imgBackButtonView.setOnClickListener{
            fragmentChange(GenresFragment.newInstance())
        }
    }

    private fun setListeners() {
        imgBackButtonView.setOnClickListener {
            fragmentChange(GenresFragment.newInstance())
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
        if (fragment is SearchFragment){
            fragment.callback = this
            hideView()
        } else {
            showView()
        }
    }

    private fun showView() {
        linearTab.visibility = View.VISIBLE
        imgPosterShow.visibility = View.VISIBLE
        tvTitleShow.visibility = View.VISIBLE
    }

    private fun hideView() {
        linearTab.visibility = View.GONE
        imgPosterShow.visibility = View.GONE
        tvTitleShow.visibility = View.GONE
    }

    private fun setupView() {
        val title = SpannableString(getString(R.string.app_name))
        val color = ForegroundColorSpan(Color.WHITE)
        title.setSpan(color, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        appTitleTextView.text = title

        linearTab = findViewById(R.id.ll_tabs_dash_movies)


        //setSearchView()

        fragmentChange(GenresFragment.newInstance())
    }

    private fun filterShow(query: String?) {
        fragmentChange(SearchFragment.newInstance(query,showSearch))
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