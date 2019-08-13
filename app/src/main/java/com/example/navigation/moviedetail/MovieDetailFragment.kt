package com.example.navigation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.navigation.common.Toaster
import com.example.navigation.databinding.MovieDetailFragmentBinding
import com.example.navigation.storage.db.models.Movie

class MovieDetailFragment : Fragment() {

    val movieDetailFragmentArgs by navArgs<MovieDetailFragmentArgs>()
    lateinit var mBinding: MovieDetailFragmentBinding
    var mListOfImages = ArrayList<String>()

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        viewModel.getGenres.observe(this@MovieDetailFragment, Observer {
            if (!it.isNullOrEmpty()) {
                setBinding(viewModel.getMovie(movieDetailFragmentArgs.movieId).apply {
                    this.genreMap = it.map { genre -> genre.id to genre.name }.toMap()
                    viewModel.setMainImagePath(this.posterPath)
                })
            }
        })
        viewModel.getPosterImages(movieDetailFragmentArgs.movieId).observe(this, Observer { it ->
            if (it.isNullOrEmpty()) {
                viewModel.fetchImages(movieId = movieDetailFragmentArgs.movieId)
            } else {
                mListOfImages.clear()
                it.map { it2 ->
                    mListOfImages.add(it2.filePath)
                }
                viewModel.updateAdapterList(mListOfImages)
            }
        })
        viewModel.getCastList(movieDetailFragmentArgs.movieId).observe(this, Observer {
            if ((it.isNullOrEmpty())) {
                viewModel.fetchMovieDetails(movieDetailFragmentArgs.movieId)
            } else {
                viewModel.updateCastAdapterList(it.filter { it3-> !it3.profilePath.isNullOrEmpty() }.toList())
            }
        })
    }

    private fun setBinding(movie: Movie) {
        mBinding.movie = movie
        mBinding.viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}
