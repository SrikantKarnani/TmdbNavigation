package com.example.navigation.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.navigation.storage.GenreRepository
import com.example.navigation.storage.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository = MovieRepository(AppDatabase.getDb(application)!!)
    private val genreRepository: GenreRepository = GenreRepository(AppDatabase.getDb(application)!!)
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val getTrendingMovies = repository.getTrendingMovieList()

    val getPopularMovies = repository.getPopularMovieList()
    val getUpcomingMovies = repository.getUpcomingMovieList()
    val getNowPlayingMovies = repository.getNowPlayingMovieList()

    val getGenres = genreRepository.getAllGenreList()
    var genreMap = mapOf<Int, String>()

    private fun refreshTrendingMovies() = viewModelScope.launch {
        repository.refreshTrendingMovies()
    }

    private fun refreshPopularMovies() = viewModelScope.launch {
        repository.refreshPopularMovies()
    }

    private fun refreshUpcomingMovies() = viewModelScope.launch {
        repository.refreshUpcomingMovies()
    }

    private fun refreshNowPlayingMovies() = viewModelScope.launch {
        repository.refreshNowPlayingMovies()
    }

    private fun refreshGenre() = viewModelScope.launch {
        genreRepository.refreshGenre()
    }

    fun createGenreMap(map: Map<Int, String>) {
        genreMap = map
    }

    suspend fun refreshMovies() {
        refreshNowPlayingMovies().join()
        refreshUpcomingMovies().join()
        refreshPopularMovies().join()
        refreshTrendingMovies().join()
    }

    fun refreshAll() {
        viewModelScope.launch {
            refreshGenre().join()
            refreshNowPlayingMovies().join()
            refreshUpcomingMovies().join()
            refreshPopularMovies().join()
            refreshTrendingMovies().join()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            refreshAll()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
