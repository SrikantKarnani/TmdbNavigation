package com.example.navigation.storage

import androidx.lifecycle.LiveData
import com.example.navigation.base.BaseRepository
import com.example.navigation.network.NetworkClient
import com.example.navigation.network.model.asDatabaseModel
import com.example.navigation.storage.db.AppDatabase
import com.example.navigation.storage.db.GenreDao
import com.example.navigation.storage.db.models.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Srikant Karnani on 26/7/19.
 */
class GenreRepository(appDatabase: AppDatabase) : BaseRepository() {
    private val networkService = NetworkClient.getWebService()
    private val genreDao: GenreDao = appDatabase.genreDao()
    private val allGenre: LiveData<List<Genre>>
    private var calendar = Calendar.getInstance()
    private val df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    private var lastDownlodedDate: String = ""

    init {
        calendar.add(Calendar.DATE, -7)
        lastDownlodedDate = AppPreferences.lastDownloaded
        val sevenDayBeforeDate = df.format(calendar.time)
        if (lastDownlodedDate.isBlank() || lastDownlodedDate < sevenDayBeforeDate)
            genreDao.deleteAll()
        allGenre = genreDao.getGenre()
    }

    fun getAllGenreList(): LiveData<List<Genre>> {
        return allGenre
    }

    suspend fun refreshGenre() {
        withContext(Dispatchers.IO) {
            val genreResponse = safeApiCall(call = { networkService.getGenre() })
            genreDao.insertAll(*genreResponse.asDatabaseModel())
            calendar = Calendar.getInstance()
            AppPreferences.lastDownloaded = df.format(calendar.time)
        }
    }
}