package com.example.navigation.storage.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Srikant Karnani on 9/8/19.
 */
@Entity
data class Crew(
    val movieId:Int,
    val creditId: String,
    val department: String,
    val gender: Int,
    @PrimaryKey
    val id: Int,
    val job: String,
    val name: String,
    val profilePath: String?
)