package com.zygotecnologia.zygotv.themoviedbapi

import org.junit.Assert
import org.junit.Test

class TheMovieDatabaseImageUrlBuilderImplTest {

    @Test
    fun testBuildPosterUrl() {
        Assert.assertEquals(
            "https://image.tmdb.org/t/p/w154/path?api_key=key",
            TheMovieDatabaseImageUrlBuilderImpl("key").getPosterCompleteUrl("/path")
        )
    }

    @Test
    fun testBuildBackdropUrl() {
        Assert.assertEquals(
            "https://image.tmdb.org/t/p/w780/path?api_key=key",
            TheMovieDatabaseImageUrlBuilderImpl("key").getBackdropCompleteUrl("/path")
        )
    }
}