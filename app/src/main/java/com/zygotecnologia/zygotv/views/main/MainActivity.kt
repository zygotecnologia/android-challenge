package com.zygotecnologia.zygotv.views.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.entity.Show
import com.zygotecnologia.zygotv.utils.BaseViewStates
import com.zygotecnologia.zygotv.utils.OnClickListener
import com.zygotecnologia.zygotv.views.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseViewStates(), OnClickListener<Show> {

    private lateinit var viewModel: MainViewModel

    private val sectionList: RecyclerView by lazy { findViewById(R.id.main_section_list) }
    private val searchView: SearchView by lazy { findViewById(R.id.app_toolbar_search_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Observe sections change & call API
        viewModel.loadSections().observe(this, {
            sectionList.adapter = SectionAdapter(it, this)
        })

        //Observe screen state to show/hide components
        viewModel.screenState.observe(this, {
            super.onStateChange(it)
        })

        val searchViewIcon: ImageView = findViewById(R.id.app_toolbar_search_icon)
        searchViewIcon.setOnClickListener { showSearchView(true) }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchShow(query ?: "")
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        val searchViewClearButton: ImageView = searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        searchViewClearButton.setOnClickListener { showSearchView(false) }
    }

    private fun showSearchView(visible: Boolean) {
        searchView.visibility = if (visible) View.VISIBLE else View.GONE
        if (visible) {
            searchView.setQuery("", false)
            searchView.requestFocusFromTouch()
        }
    }

    override fun getContent(): View = sectionList

    override fun retryAction() {
        viewModel.loadSections()
    }

    override fun onClick(obj: Show) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.DETAILS_SHOW_ID_KEY, obj.id)
        startActivity(intent)
    }

}