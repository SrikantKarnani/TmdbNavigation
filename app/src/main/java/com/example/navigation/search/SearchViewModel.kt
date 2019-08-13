package com.example.navigation.search

import android.app.Application
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : BaseViewModel(application) {
    private val searchRepository = SearchRepository(appDatabase)
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private var searchAdapter = SearchAdapter(this)

    val setOfResult = searchRepository.getSearchSet()
    var resultList = ArrayList<Any>()

    fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (!p0.isNullOrEmpty()) {
            viewModelScope.launch {
                searchRepository.getSearchResults("%$p0%")
                searchRepository.fetchSearchResults(p0.toString())
            }
        }
    }

    fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty())
            viewModelScope.launch {
                searchRepository.getSearchResults("%$query%")
                searchRepository.fetchSearchResults(query.toString())
            }
        return true
    }

    fun onQueryTextChange(newText: String?): Boolean {
        viewModelScope.launch {
            if (!newText.isNullOrEmpty())
                searchRepository.getSearchResults("%$newText%")
        }
        return true
    }

    override fun onItemClick(position: Int) {

    }

    override fun getAdapter(): RecyclerView.Adapter<*> {
        return searchAdapter
    }


    fun updateAdapterList(listOfSearchResults: Set<Any>) {
        Log.e("Search List", listOfSearchResults.size.toString())
        resultList.clear()
        resultList.addAll(listOfSearchResults)
        searchAdapter.setList(resultList)
        searchAdapter.notifyDataSetChanged()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}