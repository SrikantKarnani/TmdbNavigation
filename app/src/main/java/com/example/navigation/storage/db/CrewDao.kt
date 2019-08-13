package com.example.navigation.storage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.navigation.storage.db.models.Cast
import com.example.navigation.storage.db.models.Crew
import com.example.navigation.storage.db.models.Genre

/**
 * Created by Srikant Karnani on 26/7/19.
 */
@Dao
abstract class CrewDao : BaseDao<Crew> {

    @Query("SELECT * FROM Crew where movieId=:movieId")
    abstract fun getCrew(movieId:Int): LiveData<List<Crew>>


    @Query("DELETE FROM Crew")
    abstract suspend fun deleteAll()
}