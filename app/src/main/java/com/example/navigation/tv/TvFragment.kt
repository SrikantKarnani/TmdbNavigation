package com.example.navigation.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.navigation.R
import com.example.navigation.common.DepthTransformation
import com.example.navigation.common.ViewPagerClickHandler
import com.example.navigation.storage.db.models.Tv
import kotlinx.android.synthetic.main.tv_fragment.*

class TvFragment : Fragment() {

    companion object {
        fun newInstance() = TvFragment()
    }

    private lateinit var viewModel: TvViewModel
    private var viewPagerClickHandler = object : ViewPagerClickHandler {
        override fun onClick(tvId: Int) {

        }

        override fun onLongClick(position: Int) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TvViewModel::class.java)
        viewModel.getGenres.observe(this, Observer { it1 ->
            if (it1.isNullOrEmpty()) {
                viewModel.refreshGenre()
            } else {
                viewModel.getTv.observe(this, Observer { it2 ->
                    setUpViewPager(it2, it1.map { genre -> genre.id to genre.name }.toMap())
                })
            }
        })
    }

    private fun setUpViewPager(tvs: List<Tv>, genreMap: Map<Int, String>) {
        view_pager.adapter = TvAdapter(tvs, genreMap, viewPagerClickHandler)
        view_pager.setPageTransformer(true, DepthTransformation())
    }
}
