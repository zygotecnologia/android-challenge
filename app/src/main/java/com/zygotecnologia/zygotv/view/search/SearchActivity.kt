package com.zygotecnologia.zygotv.view.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zygotecnologia.zygotv.adapter.search.SearchShowAdapter
import com.zygotecnologia.zygotv.databinding.ActivitySearchShowBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbClient
import com.zygotecnologia.zygotv.utils.viewVisibility
import com.zygotecnologia.zygotv.viewmodel.search.SearchViewModel
import com.zygotecnologia.zygotv.viewmodel.search.SearchViewModelFactory
import com.zygotecnologia.zygotv.viewmodel.search.SearchViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity(), CoroutineScope{

    private lateinit var binding: ActivitySearchShowBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchAdapter: SearchShowAdapter

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(TmdbClient.getInstance(), coroutineContext)
        ).get(SearchViewModel::class.java)

        setupObservers()

        initShowList()

        binding.editSearchShow.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isNotBlank()) {
                    viewModel.fetchSearchShow(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupObservers() {
        viewModel.viewState.observe(this, {
            when (it) {
                is SearchViewState.Loading -> {
                    showComponents(true)
                }
                is SearchViewState.SearchSuccess -> {
                    showComponents(false)
                    updateRecyclerShow(it.showList)
                }
            }
        })
    }

    private fun showComponents(isLoading: Boolean) {
        binding.rvShowList.viewVisibility(isLoading.not())
        binding.loading.viewVisibility(isLoading)
    }

    private fun initShowList() {
        searchAdapter = SearchShowAdapter(mutableListOf())

        binding.rvShowList.apply {
            layoutManager = GridLayoutManager(
                this@SearchActivity,
                3,
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = searchAdapter
        }
    }

    private fun updateRecyclerShow(showList: List<Show>) {
        searchAdapter.updateShowList(showList)
    }
}