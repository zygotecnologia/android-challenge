package com.example.zygotv

import com.example.zygotv.presentation.ui.home.item.parent.ParentType
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert
import org.junit.Test

class ParentTypeUnitTest {
    @Test
    fun givenAParentTypeFavorite_whenItsTypeIsChecked_thenResultShouldNotBeChild() {
        val parentType = Resources.resourceParentType

        parentType.apply {
            MatcherAssert.assertThat(this, notNullValue())
            MatcherAssert.assertThat(this, instanceOf(ParentType::class.java))
            MatcherAssert.assertThat(this, `is`(ParentType.Favorite))
            MatcherAssert.assertThat(this, `is`(not(ParentType.Child)))
        }
    }
}