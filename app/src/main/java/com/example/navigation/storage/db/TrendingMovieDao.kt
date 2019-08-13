package com.example.navigation.storage.db

import androidx.room.Dao
import androidx.room.Query
import com.example.navigation.storage.db.models.TrendingMovie

/**
 * Created by Srikant Karnani on 7/8/19.
 */
@Dao
abstract class TrendingMovieDao : BaseDao<TrendingMovie> {

    @Query("DELETE FROM TrendingMovie")
    abstract fun deleteAll()

    suspend fun updateMovies(vararg trendingMovies : TrendingMovie){
        deleteAll()
        insertAll(*trendingMovies)
    }
}