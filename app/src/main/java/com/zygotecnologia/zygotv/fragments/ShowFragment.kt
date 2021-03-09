package com.zygotecnologia.zygotv.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentShowBinding
import com.zygotecnologia.zygotv.main.MainActivity
import com.zygotecnologia.zygotv.main.ShowDetailedAdapter
import com.zygotecnologia.zygotv.model.RequestStatus
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.viewmodel.MoviesViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowFragment: Fragment() {

    private var searchJob: Job? = null

    private val viewModel: MoviesViewModel by viewModel()

    protected lateinit var binding: FragmentShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val spannable: Spannable = SpannableString(getString(R.string.app_name))
        spannable.setSpan(
            ForegroundColorSpan(Color.RED),
            getString(R.string.app_name).length - 2,
            getString(R.string.app_name).length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.toolbarTitle.setText(spannable, TextView.BufferType.SPANNABLE)

        binding.toolbarTop.setOnClickListener {
            activity?.onBackPressed()
        }

        val toolbar: Toolbar =  binding.toolbarTop
        (activity as MainActivity).setSupportActionBar(toolbar)

        if (activity is MainActivity) {
            (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding.rvShowList.adapter = ShowDetailedAdapter(requireContext(), viewModel)

        getShowDetails()

        viewModel.statusPopularShows.observe(viewLifecycleOwner, Observer<RequestStatus> { status ->
            when (status) {
                RequestStatus.ERROR -> {
                    Toast.makeText(requireContext(), "Erro! Tente novamente mais tarde", Toast.LENGTH_LONG)
                        .show()
                }
                RequestStatus.EMPTY -> {
                    Toast.makeText(requireContext(), "Dados não encontrados", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })

        return binding.root
    }


    private fun getShowDetails() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            if (arguments != null) {
                val show: Show = ShowFragmentArgs.fromBundle(requireArguments()).show
                show.id?.let { viewModel.searchShowDetailed(it) }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}