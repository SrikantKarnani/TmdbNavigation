package com.example.navigation.storage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.navigation.storage.db.models.Backdrops

/**
 * Created by Srikant Karnani on 5/8/19.
 */
@Dao
abstract class BackdropDao : BaseDao<Backdrops> {

    @Query("SELECT * FROM Backdrops Where mediaId=:mId LIMIT 10")
    abstract fun getAllImages(mId:Int): LiveData<List<Backdrops>>
}