package com.example.navigation.storage.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Srikant Karnani on 9/8/19.
 */
@Entity
data class Cast(
    val movieId: Int,
    val castId: Int,
    val character: String,
    val creditId: String,
    val gender: Int,
    @PrimaryKey
    val id: Int,
    val name: String,
    val castOrder: Int,
    val profilePath: String?
)
