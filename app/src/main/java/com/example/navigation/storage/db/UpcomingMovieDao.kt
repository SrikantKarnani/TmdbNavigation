package com.example.navigation.storage.db

import androidx.room.Dao
import androidx.room.Query
import com.example.navigation.storage.db.models.TrendingMovie
import com.example.navigation.storage.db.models.UpcomingMovies

/**
 * Created by Srikant Karnani on 7/8/19.
 */
@Dao
abstract class UpcomingMovieDao : BaseDao<UpcomingMovies> {

    @Query("DELETE FROM UpcomingMovies")
    abstract fun deleteAll()

    suspend fun updateMovies(vararg upcomingMovies: UpcomingMovies){
        deleteAll()
        insertAll(*upcomingMovies)
    }
}