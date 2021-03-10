package com.zygotecnologia.zygotv.presentation.home

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.presentation.FragmentAdapter
import com.zygotecnologia.zygotv.uistate.State
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {


    private val viewModel by viewModel<MainViewModel>()

    private lateinit var navController: NavController


//    private val showList: RecyclerView by lazy { findViewById(R.id.rv_show_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager2:ViewPager2 = findViewById(R.id.viewPager)
        val tab:TabLayout = findViewById(R.id.tablayout)
        val toolbar:Toolbar = findViewById(R.id.default_toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Allef sousa"




//        loadShows()

        viewModel.showResponseLiveData.observe(this){ state ->
            when (state) {
                is State.Loading -> {
                }

                is State.Success -> {
                    if (state.data.isNotEmpty()) {
//                        showList.adapter = MainAdapter(state.data)
                    }
                }
                is State.Error -> {
                }
            }
        }

        viewPager2.adapter = FragmentAdapter(this)
        TabLayoutMediator(tab, viewPager2) { tab, position ->
            when(position){
                0 -> tab.text = "Filmes"
                1 -> tab.text = "Series"
                2 -> tab.text = "Favoritos"
            }

        }.attach()


    }

    private  fun loadShows() {
        viewModel.getHomeMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        return true
    }
}