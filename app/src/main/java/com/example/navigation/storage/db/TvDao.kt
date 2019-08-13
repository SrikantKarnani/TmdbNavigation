package com.example.navigation.storage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import com.example.navigation.storage.db.models.Tv

/**
 * Created by Srikant Karnani on 30/7/19.
 */

@Dao
abstract class TvDao : BaseDao<Tv>{

    @Query("SELECT * FROM Tv")
    abstract fun getTvs(): LiveData<List<Tv>>

    @Transaction
    open suspend fun updateTv(tvs: Tv) {
        delete(tvs)
        insert(tvs)
    }

    @Query(" SELECT * FROM Tv WHERE id= :tvId LIMIT 1")
    abstract suspend fun getTv(tvId:Int) : Tv

    @Query("DELETE FROM Tv")
    abstract suspend fun deleteAll()

    @Query("Select * from tv where title like :queryString")
    abstract suspend fun getSearched(queryString: String): List<Tv>

    @Delete
    abstract suspend fun deleteAll(Tv: List<Tv>)

    @Delete
    abstract suspend fun delete(Tv: Tv)
}