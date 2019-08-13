package com.example.navigation.moviedetail

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.base.BaseViewModel
import com.example.navigation.storage.GenreRepository
import com.example.navigation.storage.db.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : BaseViewModel(application) {
    private val movieRepository = MovieDetailRepository(appDatabase)
    private val genreRepository: GenreRepository = GenreRepository(appDatabase)
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val imagesAdapter = ImagesAdapter(this)
    private val castAdapter = CastAdapter(this)
    val getGenres = genreRepository.getAllGenreList()
    var imagesList = ArrayList<String>()
    var castImageList = ArrayList<Cast>()
    var mainImagePath = ObservableField<String>()

    fun getMovie(movieId: Int): Movie {
        return movieRepository.getMovie(movieId)
    }

    fun getBackdropImages(movieId: Int): LiveData<List<Backdrops>> {
        return movieRepository.getBackdropImages(movieId)
    }

    fun getPosterImages(movieId: Int): LiveData<List<Poster>> {
        return movieRepository.getPosterImages(movieId)
    }

    fun getCastList(movieId: Int): LiveData<List<Cast>> {
        return movieRepository.getCast(movieId)
    }

    fun getCrewList(movieId: Int): LiveData<List<Crew>> {
        return movieRepository.getCrew(movieId)
    }

    fun fetchMovieDetails(movieId: Int) = viewModelScope.launch {
        movieRepository.fetchMovieDetails(movieId)
    }

    fun fetchImages(movieId: Int) = viewModelScope.launch {
        movieRepository.fetchMovieImagesFromServer(movieId = movieId)
    }

    fun setMainImagePath(imagePath: String?) {
        mainImagePath.set(imagePath)
    }

    override fun getAdapter(): RecyclerView.Adapter<*> {
        return imagesAdapter
    }

    fun getCastAdapter(): RecyclerView.Adapter<*> {
        return castAdapter
    }

    override fun onItemClick(position: Int) {
        setMainImagePath(imagesList[position])
    }

    fun updateAdapterList(listOfImages: ArrayList<String>) {
        imagesList.clear()
        imagesList.addAll(listOfImages)
        imagesAdapter.setImages(imagesList)
        imagesAdapter.notifyDataSetChanged()
    }

    fun updateCastAdapterList(listOfImages: List<Cast>) {
        castImageList.clear()
        castImageList.addAll(listOfImages)
        castAdapter.setCast(castImageList)
        castAdapter.notifyDataSetChanged()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
