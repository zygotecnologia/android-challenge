package com.example.zygotv

import com.example.zygotv.Resources.resourceShow
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class ShowUnitTest {
    @Test
    fun givenAShow_thenResultShouldNotBeNull() {
        val show = resourceShow

        show.apply {
            MatcherAssert.assertThat(this, CoreMatchers.notNullValue())
        }
    }

}