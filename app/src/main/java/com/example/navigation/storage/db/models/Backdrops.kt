package com.example.navigation.storage.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Srikant Karnani on 5/8/19.
 */
@Entity
data class Backdrops(
    val mediaId: Int,
    val aspectRatio: Double,
    val filePath: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}