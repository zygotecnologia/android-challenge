package com.zygotecnologia.zygotv.presentation.di.home

import com.zygotecnologia.zygotv.presentation.ui.MainActivity
import com.zygotecnologia.zygotv.presentation.ui.home.HomeFragment
import dagger.Subcomponent

@HomeScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent{

    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): HomeSubComponent
    }
}