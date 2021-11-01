package com.zygotecnologia.zygotv.viewmodel.description

import com.zygotecnologia.zygotv.model.Episodes
import com.zygotecnologia.zygotv.model.Show

sealed class DescriptionViewState {
    data class Loading(val loadingSeasonEpisodes: Boolean = false) : DescriptionViewState()
    data class ShowDescription(val showDescription: Show) : DescriptionViewState()
    data class SeasonEpisodes(val episodes: List<Episodes>) : DescriptionViewState()
    object Error : DescriptionViewState()
}