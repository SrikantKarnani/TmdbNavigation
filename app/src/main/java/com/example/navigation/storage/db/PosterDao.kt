package com.example.navigation.storage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.navigation.storage.db.models.Poster

/**
 * Created by Srikant Karnani on 5/8/19.
 */
@Dao
abstract class PosterDao : BaseDao<Poster> {

    @Query("SELECT * FROM Poster Where mediaId=:mId LIMIT 10")
    abstract fun getAllImages(mId: Int): LiveData<List<Poster>>
}