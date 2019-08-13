package com.example.navigation.search

import android.util.SparseArray
import androidx.core.util.set
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.navigation.base.BaseRepository
import com.example.navigation.network.NetworkClient
import com.example.navigation.network.TmdbService
import com.example.navigation.network.model.asDatabaseMovieModel
import com.example.navigation.network.model.asDatabaseTvModel
import com.example.navigation.storage.db.AppDatabase
import com.example.navigation.storage.db.MovieDao
import com.example.navigation.storage.db.TvDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Srikant Karnani on 7/8/19.
 */
class SearchRepository(appDatabase: AppDatabase) : BaseRepository() {

    private val tvDao: TvDao = appDatabase.tvDao()
    private val movieDao: MovieDao = appDatabase.movieDao()
    private val networkService = NetworkClient.getWebService()
    private val searchSet : MutableLiveData<HashSet<Any>> = MutableLiveData()

    init {
        searchSet.value = HashSet()
    }

    suspend fun getSearchResults(queryString: String){
        val tempSet = HashSet<Any>()
        tempSet.addAll(movieDao.getSearched(queryString))
        tempSet.addAll(tvDao.getSearched(queryString))
        searchSet.postValue(tempSet)
    }

    fun getSearchSet():LiveData<HashSet<Any>>{
        return searchSet
    }

    suspend fun fetchSearchResults(queryString: String){
        withContext(Dispatchers.IO) {
            val searchResponse = safeApiCall { networkService.getSearchResults(queryString) }
            tvDao.insertAll(*searchResponse.asDatabaseTvModel())
            movieDao.insertAll(*searchResponse.asDatabaseMovieModel())
            getSearchResults("%$queryString%")
        }
    }


}