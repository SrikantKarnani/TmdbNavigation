package com.example.navigation.tv

import androidx.lifecycle.LiveData
import com.example.navigation.base.BaseRepository
import com.example.navigation.network.NetworkClient
import com.example.navigation.network.model.asDatabaseModel
import com.example.navigation.storage.db.AppDatabase
import com.example.navigation.storage.db.TvDao
import com.example.navigation.storage.db.models.Tv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Created by Srikant Karnani on 29/7/19.
 */
class TvRepository(appDatabase: AppDatabase) : BaseRepository() {
    private val networkService = NetworkClient.getWebService()
    private val tvDao: TvDao = appDatabase.tvDao()
    private val allTvs: LiveData<List<Tv>>

    init {
        allTvs = tvDao.getTvs()
    }

    suspend fun refreshTv() {
        withContext(Dispatchers.IO) {
            val tvResponse = safeApiCall { networkService.getTrendingTv() }
            tvDao.insertAll(*tvResponse.asDatabaseModel())
        }
    }

    fun getAllTv(): LiveData<List<Tv>> {
        return allTvs
    }

    fun getTv(tvId: Int): Tv = runBlocking {
        tvDao.getTv(tvId)
    }
}