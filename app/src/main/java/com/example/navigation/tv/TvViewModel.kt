package com.example.navigation.tv

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.navigation.storage.GenreRepository
import com.example.navigation.storage.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TvViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TvRepository(AppDatabase.getDb(application)!!)
    private val genreRepository = GenreRepository(AppDatabase.getDb(application)!!)
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val getTv = repository.getAllTv()

    val getGenres = genreRepository.getAllGenreList()

    init {
        refreshData()
    }

    fun refreshData() = viewModelScope.launch {
        repository.refreshTv()
    }

    fun refreshGenre() = viewModelScope.launch {
        genreRepository.refreshGenre()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
