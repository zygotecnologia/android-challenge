package com.zygotecnologia.zygotv.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.presentation.adapter.FragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

        val viewpager = viewPager
        val tablayout = tab_layoutopc

        viewpager.adapter = FragmentAdapter(this)
        viewpager.isUserInputEnabled=false

        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> "Filmes"
            1 -> "Séries"
            2 -> "Favoritos"
            else -> "Filmes"
        }
    }
}