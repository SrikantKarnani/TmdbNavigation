package com.example.navigation.storage.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Srikant Karnani on 7/8/19.
 */
@Entity
data class PopularMovie(@PrimaryKey val movieId: Int)