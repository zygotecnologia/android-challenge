package com.zygotecnologia.zygotv.presentation.di.detail

import com.zygotecnologia.zygotv.presentation.ui.detail.DetailFragment
import dagger.Subcomponent

@DetailScope
@Subcomponent(modules = [DetailModule::class])
interface DetailSubComponent {

    fun inject(fragment: DetailFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailSubComponent
    }
}