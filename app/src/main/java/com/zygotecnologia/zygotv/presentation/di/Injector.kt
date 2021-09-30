package com.zygotecnologia.zygotv.presentation.di

import com.zygotecnologia.zygotv.presentation.di.detail.DetailSubComponent
import com.zygotecnologia.zygotv.presentation.di.home.HomeSubComponent

interface Injector {

    fun createHomeSubComponent(): HomeSubComponent

    fun createDetailSubComponent(): DetailSubComponent
}