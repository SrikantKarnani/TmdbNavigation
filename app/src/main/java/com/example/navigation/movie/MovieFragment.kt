package com.example.navigation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.navigation.R
import com.example.navigation.common.DepthTransformation
import com.example.navigation.common.ViewPagerClickHandler
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.movie_fragment.*


class MovieFragment : Fragment() {
    private lateinit var viewModel: MovieViewModel
    private lateinit var nowPlayingAdapter: MovieAdapter
    private lateinit var trendingAdapter: MovieAdapter
    private lateinit var upcomingAdapter: MovieAdapter
    private lateinit var popularAdapter: MovieAdapter
    private var viewPagerClickHandler = object : ViewPagerClickHandler {
        override fun onClick(movieId: Int) {
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movieId))
        }

        override fun onLongClick(position: Int) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel.getGenres.observe(this, Observer { it1 ->
            if (it1.isNullOrEmpty()) {
                viewModel.refreshAll()
            } else {
                viewModel.createGenreMap(it1.map { genre -> genre.id to genre.name }.toMap())
            }
        })
        viewModel.getNowPlayingMovies.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                nowPlayingAdapter = MovieAdapter(it, viewModel.genreMap, viewPagerClickHandler)
                setUpViewPager(nowPlayingAdapter)
            }
        })
        viewModel.getTrendingMovies.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                trendingAdapter = MovieAdapter(it, viewModel.genreMap, viewPagerClickHandler)
            }
        })
        viewModel.getUpcomingMovies.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                upcomingAdapter = MovieAdapter(it, viewModel.genreMap, viewPagerClickHandler)
            }
        })
        viewModel.getPopularMovies.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                popularAdapter = MovieAdapter(it, viewModel.genreMap, viewPagerClickHandler)
            }
        })
    }

    private fun setUpViewPager(adapter: MovieAdapter) {
        view_pager.adapter = adapter
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.setPageTransformer(true, DepthTransformation())
        sliding_tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                switchViewPager(tab!!.position)
            }
        })
    }

    private fun switchViewPager(position: Int) {
        when (position) {
            0 -> {
                if (::nowPlayingAdapter.isInitialized)
                    setUpViewPager(nowPlayingAdapter)
            }
            1 -> {
                if (::trendingAdapter.isInitialized)
                    setUpViewPager(trendingAdapter)
            }
            2 -> {
                if (::upcomingAdapter.isInitialized)
                    setUpViewPager(upcomingAdapter)
            }
            3 -> {
                if (::popularAdapter.isInitialized)
                    setUpViewPager(popularAdapter)
            }
        }
    }
}
