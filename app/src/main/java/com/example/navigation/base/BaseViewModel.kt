package com.example.navigation.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.storage.db.AppDatabase

/**
 * Created by Srikant Karnani on 7/8/19.
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val appDatabase = AppDatabase.getDb(application)!!

    abstract fun onItemClick(position: Int)

    abstract fun getAdapter() : RecyclerView.Adapter<*>

}