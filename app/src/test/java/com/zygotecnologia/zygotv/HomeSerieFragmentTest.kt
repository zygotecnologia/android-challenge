package com.zygotecnologia.zygotv

import com.zygotecnologia.zygotv.data.remote.TmdbApi
import com.zygotecnologia.zygotv.data.repository.ZygoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


//class HomeSerieFragmentTest {

//    @InjectMocks
//    var repository : ZygoRepository? = ZygoRepository(get())

//    @Before
//    fun setUp(){
//        MockitoAnnotations.initMocks(this)
//    }

//    @Test
//    fun `teste chamada getSerie do respository`() = runBlocking {
//        launch(Dispatchers.Default) { verify(repository)?.getSerie("BR")}
//    }

//}