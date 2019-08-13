package com.example.navigation.moviedetail

import androidx.lifecycle.LiveData
import com.example.navigation.base.BaseRepository
import com.example.navigation.network.NetworkClient
import com.example.navigation.network.model.asBackdropDataModel
import com.example.navigation.network.model.asCastDatabaseModel
import com.example.navigation.network.model.asCrewDatabaseModel
import com.example.navigation.network.model.asPosterDataModel
import com.example.navigation.storage.db.*
import com.example.navigation.storage.db.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Created by Srikant Karnani on 26/7/19.
 */
class MovieDetailRepository(appDatabase: AppDatabase) : BaseRepository() {
    private val networkService = NetworkClient.getWebService()
    private val movieDao: MovieDao = appDatabase.movieDao()
    private val backdropDao: BackdropDao = appDatabase.backdropDao()
    private val posterDao: PosterDao = appDatabase.posterDao()
    private val castDao: CastDao = appDatabase.castDao()
    private val crewDao: CrewDao = appDatabase.crewDao()


    fun getMovie(movieId: Int): Movie = runBlocking {
        movieDao.getMovie(movieId)
    }

    suspend fun fetchMovieDetails(movieId: Int) {
        withContext(Dispatchers.IO) {
            val detailsResponse = safeApiCall { networkService.getMovieDetails(movieId) }
            castDao.insertAll(*detailsResponse.asCastDatabaseModel())
            crewDao.insertAll(*detailsResponse.asCrewDatabaseModel())
        }
    }

    fun getCast(movieId:Int) : LiveData<List<Cast>>{
        return castDao.getCast(movieId)
    }

    fun getCrew(movieId:Int) : LiveData<List<Crew>>{
        return crewDao.getCrew(movieId)
    }

    fun getBackdropImages(movieId: Int): LiveData<List<Backdrops>> {
        return backdropDao.getAllImages(movieId)
    }

    fun getPosterImages(movieId: Int): LiveData<List<Poster>> {
        return posterDao.getAllImages(movieId)
    }

    suspend fun fetchMovieImagesFromServer(movieId: Int) {
        withContext(Dispatchers.IO) {
            val results = safeApiCall(
                call = { networkService.getImagesOfMovie(movieId) })
            backdropDao.insertAll(*results.asBackdropDataModel())
            posterDao.insertAll(*results.asPosterDataModel())
        }
    }


}