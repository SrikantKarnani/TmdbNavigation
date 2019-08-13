package com.example.navigation.storage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.navigation.storage.db.models.Genre

/**
 * Created by Srikant Karnani on 26/7/19.
 */
@Dao
abstract class GenreDao : BaseDao<Genre> {

    @Query("SELECT * FROM GENRE")
    abstract fun getGenre(): LiveData<List<Genre>>


    @Query(" SELECT * FROM GENRE WHERE id= :genreId LIMIT 1")
    abstract suspend fun getGenre(genreId: Int): Genre?

    @Query(" SELECT * FROM GENRE WHERE GENRE.id IN(:genreList) ")
    abstract suspend fun getGenre(genreList: List<Int>): List<Genre>?

    @Query("DELETE FROM GENRE")
    abstract fun deleteAll()

    @Delete
    abstract suspend fun deleteAll(genre: List<Genre>)

    @Delete
    abstract suspend fun delete(genre: Genre)

}