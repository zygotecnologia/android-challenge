package com.zygotecnologia.zygotv.series.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.repository.SeriesRepository
import com.zygotecnologia.zygotv.series.viewmodel.SeriesViewModel
import com.zygotecnologia.zygotv.series.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchFragment(val query: String?) : Fragment() {

    private val mViewModel: SeriesViewModel by viewModel {
        parametersOf(SeriesRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        query.let { mViewModel.getSearch(it!!) }
    }

    private fun initObserver() {
        mViewModel.dataSearch.observe(viewLifecycleOwner, {
            rv_show_list.layoutManager = LinearLayoutManager(context)
            rv_show_list.adapter = SearchAdapter(it)
        })
    }
}