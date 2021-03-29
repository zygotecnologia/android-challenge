package com.zygotecnologia.zygotv.views.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.entity.Show
import com.zygotecnologia.zygotv.utils.BaseViewStates
import com.zygotecnologia.zygotv.utils.OnClickListener
import com.zygotecnologia.zygotv.utils.ScreenState
import com.zygotecnologia.zygotv.views.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseViewStates(), OnClickListener<Show> {

    private lateinit var viewModel: MainViewModel

    private val sectionList: RecyclerView by lazy { findViewById(R.id.main_section_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Observe sections change & call API
        viewModel.loadSections().observe(this, {
            sectionList.adapter = SectionAdapter(it, this)
        })

        //Observe screen state to show/hide components
        viewModel.screenState.observe(this, {
            super.onStateChange(it)
        })
    }

    override fun getContent(): View = sectionList

    override fun retryAction() {
        viewModel.loadSections()
    }

    override fun onClick(obj: Show) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.DETAILS_SHOW_ID_KEY, obj.id)
        startActivity(intent)
    }

}