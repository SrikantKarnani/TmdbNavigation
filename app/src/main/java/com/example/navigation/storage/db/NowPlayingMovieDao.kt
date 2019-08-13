package com.example.navigation.storage.db

import androidx.room.Dao
import androidx.room.Query
import com.example.navigation.storage.db.models.NowPlayingMovie

/**
 * Created by Srikant Karnani on 7/8/19.
 */
@Dao
abstract class NowPlayingMovieDao : BaseDao<NowPlayingMovie> {

    @Query("DELETE FROM NowPlayingMovie")
    abstract fun deleteAll()

    suspend fun updateMovies(vararg nowPlayingMovie: NowPlayingMovie) {
        deleteAll()
        insertAll(*nowPlayingMovie)
    }
}