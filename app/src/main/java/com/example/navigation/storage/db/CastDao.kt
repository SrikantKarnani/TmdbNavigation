package com.example.navigation.storage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.navigation.storage.db.models.Cast

/**
 * Created by Srikant Karnani on 26/7/19.
 */
@Dao
abstract class CastDao : BaseDao<Cast> {

    @Query("SELECT * FROM `Cast` where movieId=:movieId order by castOrder asc limit 5")
    abstract fun getCast(movieId:Int): LiveData<List<Cast>>


    @Query("DELETE FROM Cast")
    abstract suspend fun deleteAll()
}