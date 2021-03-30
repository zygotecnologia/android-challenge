package com.zygotecnologia.zygotv.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val SHOWS_LIST = "show_list"

@AndroidEntryPoint
class GenresFragment : Fragment(), ClickedShow {

    internal lateinit var callback: FragmentListener

    private val viewModel: DashboardViewModel by viewModels()

    private lateinit var rvGenreAndShows: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shows_per_genre, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() {
        view?.let {
            rvGenreAndShows = it.findViewById(R.id.rv_show_genre_and_show)
        }

    }

    private fun setupObservers() {
        viewModel.mutableGenreAndShowList.observe(requireActivity(), {
            it?.let {
                rvGenreAndShows.adapter = GenreAndShowAdapter(it, this)
            }
        })

       viewModel.mutableShow.observe( requireActivity(), {
            callback.nextFragment(ShowFragment.newInstance(it))
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GenresFragment()
    }

    override fun show(show: ShowDetails) {
        Toast.makeText(requireContext(), show.id.toString(), Toast.LENGTH_LONG).show()
        show.id?.let {
            viewModel.loadShowDetails(it)
        }
    }


}
interface FragmentListener {
    fun nextFragment(fragment: Fragment)
}