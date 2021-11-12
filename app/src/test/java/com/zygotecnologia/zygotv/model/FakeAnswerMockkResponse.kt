package com.zygotecnologia.zygotv.model

class FakeAnswerMockkResponse {
    fun getShowResponseForMockRepository() : ShowResponse{
        return ShowResponse(
            page = 1,
            totalResults = 1,
            totalPages = 1,
            results = listOf(
                Show(
                genres = null,
                originalName = "Chucky",
                    genreIds = listOf(1),
                    name = "Chucky",
                    voteCount = null,
                    backdropPath = "/xAKMj134XHQVNHLC6rWsccLMenG.jpg",
                    originalLanguage = "BR",
                    id = 123,
                    overview = null,
                    posterPath = "/iF8ai2QLNiHV4anwY1TuSGZXqfN.jpg"
            ))
        )
    }

    fun getGenreResponseForMockRepository() : GenreResponse{
        return GenreResponse(
            listOf(
                Genre(
                id = 1,
                    name = "Thriller"
            ))
        )
    }

    fun getShowDetailsResponseForMockRepository() : ShowDetails{
        return ShowDetails(
            backdrop_path = "/xAKMj134XHQVNHLC6rWsccLMenG.jpg",
            id = 13596,
            name = "Chucky",
            original_name = "Chucky",
            overview = null,
            poster_path = "/iF8ai2QLNiHV4anwY1TuSGZXqfN.jpg",
            seasons = null
            )
    }
}