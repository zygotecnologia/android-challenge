package com.zygotecnologia.zygotv.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.zygotecnologia.zygotv.data.repository.BaseRepository
import com.zygotecnologia.zygotv.network.ClientConfig
import com.zygotecnologia.zygotv.ui.viewmodel.ViewModelFactory

abstract class BaseFragment<VM: ViewModel, B: ViewBinding, R: BaseRepository>: Fragment() {

    protected lateinit var bindig: B
    protected lateinit var viewModel: VM

    protected var remoteDataSource = ClientConfig()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindig = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(
            getFragmentRepository()
        )
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        return bindig.root
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getFragmentRepository(): R
}