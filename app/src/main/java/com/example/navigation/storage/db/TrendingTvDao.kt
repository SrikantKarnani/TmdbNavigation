package com.example.navigation.storage.db

import androidx.room.Dao
import androidx.room.Query
import com.example.navigation.storage.db.models.TrendingTv

/**
 * Created by Srikant Karnani on 7/8/19.
 */
@Dao
abstract class TrendingTvDao : BaseDao<TrendingTv> {

    @Query("DELETE FROM TrendingTv")
    abstract fun deleteAll()

    suspend fun updatePopularMovies(trendingTvs: Array<TrendingTv>) {
        deleteAll()
        insertAll(*trendingTvs)
    }

}