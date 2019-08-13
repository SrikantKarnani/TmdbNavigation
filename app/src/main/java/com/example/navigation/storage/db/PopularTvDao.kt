package com.example.navigation.storage.db

import androidx.room.Dao
import androidx.room.Query
import com.example.navigation.storage.db.models.PopularTv

/**
 * Created by Srikant Karnani on 7/8/19.
 */

@Dao
abstract class PopularTvDao : BaseDao<PopularTv> {

    @Query("DELETE FROM PopularTv")
    abstract fun deleteAll()

    suspend fun updatePopularMovies(popularMovies : Array<PopularTv>){
        deleteAll()
        insertAll(*popularMovies)
    }
}