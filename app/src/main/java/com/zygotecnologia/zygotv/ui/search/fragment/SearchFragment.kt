package com.zygotecnologia.zygotv.ui.search.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.databinding.SearchFragmentBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.ui.search.adapter.SearchListAdapter
import com.zygotecnologia.zygotv.ui.search.viewmodel.SearchViewModel
import com.zygotecnologia.zygotv.utils.testConnection
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: SearchFragmentBinding
    private val viewModel: SearchViewModel by viewModel()
    private val listFilter: MutableList<List<Show>> = mutableListOf()
    private val listSearch: MutableList<Show> = mutableListOf()

    companion object {
        const val TAG = "SEARCH CONNECTION NETWORK"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testConnection(view, viewLifecycleOwner,
            isConnection = {
                Log.i(TAG, "Internet ON")
                observeEvents()
            }, notConnection = {
                Log.i(TAG, "Internet OFF")
                val snackbar =
                    Snackbar.make(binding.root, "Sem Internet", Snackbar.LENGTH_SHORT)
                snackbar.show()
            })

        setupLayout()
    }

    private fun setupLayout() {
        binding.searchEdittext.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.itemDetailsRecyclerSeasons.run {
                    listFilter.map { listShow ->
                        listSearch.clear()
                        listShow.forEach { show ->
                            if (show.name?.toLowerCase()
                                    ?.contains(charSequence!!.toString().toLowerCase())!!
                            ) {
                                listSearch.add(show)
                            }
                        }
                    }
                    setHomeRecycler(listSearch)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun observeEvents() {
        viewModel.listPopularShow.observe(viewLifecycleOwner, { listShow ->
            listFilter.add(listShow)
            setHomeRecycler(listShow)
        })
        viewModel.loadPopularShow()
    }

    private fun setHomeRecycler(list: List<Show>) {
        val listRecycler = listSearch as List<Show>
        if (!listRecycler.contains(list)) {
            binding.itemDetailsRecyclerSeasons.run {
                adapter = SearchListAdapter(context, list)
            }
        }
    }
}