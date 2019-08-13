package com.example.navigation.storage.db.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Created by Srikant Karnani on 25/7/19.
 */

@Entity
data class Movie(
    val adult: Boolean,
    val backdropPath: String?,
    var genreIds: ArrayList<Int>,
    @PrimaryKey
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) : BaseObservable() {

    @Ignore
    var genreMap: Map<Int, String> = HashMap()

    @Bindable
    fun getGenreList(): String {
        val genreBuilder = StringBuilder()
        for (genreId in genreIds) {
            if (genreMap.containsKey(genreId))
                genreBuilder.append(genreMap[genreId] + ",")
        }
        if (genreBuilder.isEmpty())
            return ""
        return genreBuilder.substring(0, genreBuilder.length - 1)
    }
}