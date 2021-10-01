package com.example.zygotv

import com.example.zygotv.Resources.resourceShow
import com.example.zygotv.ext.toChildItem
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class MutableListExtUnitTest {

    @Test
    fun givenMutableListWithOneShow_whenToChildItemIsCalled_thenResultSizeShouldBeOne() {
        val list = mutableListOf(
            resourceShow
        )

        val result = list.toChildItem()

        MatcherAssert.assertThat(result.size, CoreMatchers.`is`(1))
    }
}