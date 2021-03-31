package com.zygotecnologia.zygotv.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.api.TmdbApi
import com.zygotecnologia.zygotv.model.*
import com.zygotecnologia.zygotv.usecase.DashboardUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @ViewModelInject @Inject constructor(private val dashboardUseCase: DashboardUseCase) :
    ViewModel() {

    val mutableListOfShowDetails: MutableLiveData<List<Show>> = MutableLiveData()
    val mutableListOsSeasonDetail: MutableLiveData<List<Season>> = MutableLiveData()
    val mutableSeasonAndEpisodeMap: MutableLiveData<HashMap<Season, List<Episode>>> =
        MutableLiveData()
    val mutableGenreAndShowList: MutableLiveData<List<GenreAndShows>> = MutableLiveData()
    val mutableError: MutableLiveData<String> = MutableLiveData()
    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData()


    fun loadGenres() {
        showLoading()
        viewModelScope.launch {
            var genre: List<Genre> = emptyList()
            var movies: ShowResponse? = null
            dashboardUseCase.getGenre(TmdbApi.TMDB_API_KEY, "BR").let {
                when (it) {
                    is ApiResponse.Sucess -> genre = it.data
                    is ApiResponse.Failure -> mutableError.postValue(it.exception.message)
                }
            }
            dashboardUseCase.getPopularShow(TmdbApi.TMDB_API_KEY, "BR").let {
                when (it) {
                    is ApiResponse.Sucess -> movies = it.data
                    is ApiResponse.Failure -> mutableError.postValue(it.exception.message)
                }
            }
            movies?.let {
                makeGenreAndShowList(genre, it)
            }
        }
    }


    fun loadListOfShows() {
        viewModelScope.launch {
            dashboardUseCase.getPopularShow(TmdbApi.TMDB_API_KEY, "BR").let {
                when (it) {
                    is ApiResponse.Sucess -> mutableListOfShowDetails.postValue(it.data.results)
                    is ApiResponse.Failure -> mutableError.postValue(it.exception.message)
                }
            }
        }
    }

    fun loadShowDetails(id: Int) {
        viewModelScope.launch {
            dashboardUseCase.getShow(id, TmdbApi.TMDB_API_KEY).let {
                when (it) {
                    is ApiResponse.Sucess -> {
                        loadSeasonDetails(it.data)
                    }
                    is ApiResponse.Failure -> mutableError.postValue(it.exception.message)
                }
            }
        }
    }

    fun loadSeasonDetails(showDetails: ShowDetails) {
        val seasonList: MutableList<Season> = mutableListOf()
        viewModelScope.launch {
            showDetails.seasons?.forEach { season ->
                if (showDetails.id != null && season.seasonNumber != null) {
                    dashboardUseCase.getSeason(
                        showDetails.id,
                        season.seasonNumber,
                        TmdbApi.TMDB_API_KEY
                    )
                        .let {
                            when (it) {
                                is ApiResponse.Sucess -> {
                                    seasonList.add(it.data)

                                }
                                is ApiResponse.Failure -> mutableError.postValue(it.exception.message)
                            }
                        }
                }
            }
            mutableListOsSeasonDetail.postValue(seasonList)
            loadEpisodesDetails(showDetails, seasonList)
        }
    }

    fun loadEpisodesDetails(showDetails: ShowDetails, seasonDetailList: List<Season>) {
        val episodeList: MutableList<Episode> = mutableListOf()
        val seasonAndEpisodeMap: HashMap<Season, List<Episode>> = HashMap()
        viewModelScope.launch {
        seasonDetailList.forEach { seasonDetailList ->
            seasonDetailList.episodes?.forEach { episode ->
                    if (showDetails.id != null && seasonDetailList.seasonNumber != null && episode.episodeNumber != null) {
                        dashboardUseCase.getEpisode(
                            showDetails.id,
                            seasonDetailList.seasonNumber,
                            episode.episodeNumber,
                            TmdbApi.TMDB_API_KEY
                        ).let {
                            when (it) {
                                is ApiResponse.Sucess -> {
                                    episodeList.add(it.data)
                                }
                                is ApiResponse.Failure -> mutableError.postValue(it.exception.message)
                            }
                        }
                    }
                }
            seasonAndEpisodeMap.put(seasonDetailList, episodeList)
        }
            mutableSeasonAndEpisodeMap.postValue(seasonAndEpisodeMap)
        }
    }

    private fun makeGenreAndShowList(listGenre: List<Genre>, listShow: ShowResponse) {
        val listGenreAndShow: MutableList<GenreAndShows> = mutableListOf()
        val listShow = listShow.results
        listGenre.forEach { genre ->
            genre.id?.let { genreId ->
                val list = listShow?.filter { show ->
                    show.genreIds?.contains(genreId) == true
                }
                listGenreAndShow.add(GenreAndShows(genre.name, list))
            }
        }
        mutableGenreAndShowList.postValue(listGenreAndShow)
    }

    /*private fun setListMovies(genre: List<Genre>?, showResponse: ShowResponse?) {
        mutableGenreList.postValue(genre)
        val listOfShow = genre?.let { genre ->
            showResponse?.let { show ->
                show.results?.map { show ->
                    show.copy(genres = genre.filter { show.genreIds?.contains(it.id) == true })
                }
            }
        }
        mutableListOfShow.postValue(listOfShow)
    }*/

    private fun showLoading() {
        mutableIsLoading.postValue(true)
    }

}