package com.example.navigation.storage.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Created by Srikant Karnani on 25/7/19.
 */

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg obj: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg obj: T)

}