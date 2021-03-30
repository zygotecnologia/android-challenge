package com.zygotecnologia.zygotv.main

import com.zygotecnologia.zygotv.model.ShowDetails

interface ClickedShow {
    fun show(show: ShowDetails)
}