package com.example.navigation.storage.db

import androidx.room.Dao
import androidx.room.Query
import com.example.navigation.storage.db.models.PopularMovie

/**
 * Created by Srikant Karnani on 7/8/19.
 */
@Dao
abstract class PopularMovieDao : BaseDao<PopularMovie> {

    @Query("DELETE FROM POPULARMOVIE")
    abstract fun deleteAll()

    suspend fun updateMovies(vararg popularMovies: PopularMovie) {
        deleteAll()
        insertAll(*popularMovies)
    }


}