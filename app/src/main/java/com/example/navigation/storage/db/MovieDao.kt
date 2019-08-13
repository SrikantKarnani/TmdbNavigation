package com.example.navigation.storage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import com.example.navigation.storage.db.models.Movie

/**
 * Created by Srikant Karnani on 25/7/19.
 */
@Dao
abstract class MovieDao : BaseDao<Movie> {

    @Query("SELECT * FROM movie")
    abstract fun getMovies(): LiveData<List<Movie>>


    @Query("SELECT * FROM movie join PopularMovie on PopularMovie.movieId = Movie.id order by movie.popularity desc")
    abstract fun getPopularMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie join TrendingMovie on TrendingMovie.movieId = Movie.id order by movie.popularity desc")
    abstract fun getTrendingMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie join UpcomingMovies on UpcomingMovies.movieId = Movie.id order by movie.popularity desc")
    abstract fun getUpcomingMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie join NowPlayingMovie on NowPlayingMovie.movieId = Movie.id order by movie.popularity desc")
    abstract fun getNowPlayingMovies(): LiveData<List<Movie>>

    @Transaction
    open suspend fun updateMovie(movie: Movie) {
        delete(movie)
        insert(movie)
    }

    @Query("SELECT * FROM MOVIE WHERE id= :movieId LIMIT 1")
    abstract suspend fun getMovie(movieId: Int): Movie

    @Query("DELETE FROM MOVIE")
    abstract suspend fun deleteAll()

    @Query("Select * from movie where title like :queryString")
    abstract suspend fun getSearched(queryString: String): List<Movie>

    @Delete
    abstract suspend fun deleteAll(movies: List<Movie>)

    @Delete
    abstract suspend fun delete(movie: Movie)

}