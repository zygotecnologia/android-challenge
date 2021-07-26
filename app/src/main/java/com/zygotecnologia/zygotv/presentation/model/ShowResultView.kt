package com.zygotecnologia.zygotv.presentation.model

import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.domain.model.Show

class ShowResultView(
    val mostPopular: Show?,
    val genre: List<Genre>,
    val showByGender: Map<Genre, List<Show>>
    )