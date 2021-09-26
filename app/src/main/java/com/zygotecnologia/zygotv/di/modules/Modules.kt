package com.zygotecnologia.zygotv.di.modules

import com.zygotecnologia.zygotv.data.repository.TmdbRepository
import com.zygotecnologia.zygotv.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {

   single {
       TmdbRepository()
   }

    viewModel{
        MainViewModel(
            repository = get()
        )
    }
}