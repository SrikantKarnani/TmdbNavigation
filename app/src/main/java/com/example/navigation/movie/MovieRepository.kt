package com.example.navigation.movie

import androidx.lifecycle.LiveData
import com.example.navigation.base.BaseRepository
import com.example.navigation.network.NetworkClient
import com.example.navigation.network.model.*
import com.example.navigation.storage.db.*
import com.example.navigation.storage.db.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Srikant Karnani on 26/7/19.
 */
class MovieRepository(appDatabase: AppDatabase) : BaseRepository() {
    private val networkService = NetworkClient.getWebService()
    private val movieDao: MovieDao = appDatabase.movieDao()
    private val popularMovieDao: PopularMovieDao = appDatabase.popularMovieDao()
    private val trendingMovieDao: TrendingMovieDao = appDatabase.trendingMovieDao()
    private val upcomingMovieDao: UpcomingMovieDao = appDatabase.upcomingMovieDao()
    private val nowPlayingMovieDao: NowPlayingMovieDao = appDatabase.nowPlayingMovieDao()
    private val trendingMovies: LiveData<List<Movie>>
    private val popularMovies: LiveData<List<Movie>>
    private val upcomingMovies: LiveData<List<Movie>>
    private val nowPlayingMovies: LiveData<List<Movie>>
    private val calendar = Calendar.getInstance()
    private val df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    private val startDate: String
    private val endDate: String

    init {

        trendingMovies = movieDao.getTrendingMovies()
        popularMovies = movieDao.getPopularMovies()
        upcomingMovies = movieDao.getUpcomingMovies()
        nowPlayingMovies = movieDao.getNowPlayingMovies()
        calendar.add(Calendar.DATE,-14)
        startDate = df.format(calendar.time)
        calendar.add(Calendar.DATE,14)
        endDate = df.format(calendar.time)
    }

    suspend fun refreshTrendingMovies() {
        withContext(Dispatchers.IO) {
            val results = safeApiCall(
                call = { networkService.getTrendingMovies() })
            movieDao.insertAll(*results.asDatabaseModel())
            trendingMovieDao.updateMovies(*results.asTrendingDatabaseModel())
        }
    }

    suspend fun refreshPopularMovies() {
        withContext(Dispatchers.IO) {
            val results = safeApiCall(
                call = { networkService.getPopularMovies() })
            movieDao.insertAll(*results.asDatabaseModel())
            popularMovieDao.updateMovies(*results.asPopularDatabaseModel())
        }
    }

    suspend fun refreshUpcomingMovies() {
        withContext(Dispatchers.IO) {
            val results = safeApiCall(
                call = { networkService.getUpcomingMovies() })
            movieDao.insertAll(*results.asDatabaseModel())
            upcomingMovieDao.updateMovies(*results.asUpcomingDatabaseModel())
        }
    }

    suspend fun refreshNowPlayingMovies() {
        withContext(Dispatchers.IO) {
            val results = safeApiCall(
                call = { networkService.getNowPlayingMovies(startDate = startDate, endDate = endDate) })
            movieDao.insertAll(*results.asDatabaseModel())
            nowPlayingMovieDao.updateMovies(*results.asNowPlayingDatabaseModel())
        }
    }

    fun getTrendingMovieList(): LiveData<List<Movie>> {
        return trendingMovies
    }

    fun getPopularMovieList(): LiveData<List<Movie>> {
        return popularMovies
    }

    fun getUpcomingMovieList(): LiveData<List<Movie>> {
        return upcomingMovies
    }

    fun getNowPlayingMovieList(): LiveData<List<Movie>> {
        return nowPlayingMovies
    }

    fun getMovie(movieId: Int): Movie = runBlocking {
        movieDao.getMovie(movieId)
    }

}