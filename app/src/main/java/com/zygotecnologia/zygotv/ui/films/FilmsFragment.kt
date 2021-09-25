package com.zygotecnologia.zygotv.ui.films

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zygotecnologia.zygotv.R

class FilmsFragment : Fragment() {

    companion object {
        fun newInstance() = FilmsFragment()
    }

    private lateinit var viewModel: FilmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.films_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilmsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}