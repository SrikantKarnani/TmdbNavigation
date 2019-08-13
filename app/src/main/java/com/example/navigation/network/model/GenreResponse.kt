package com.example.navigation.network.model

import com.example.navigation.storage.db.models.Genre

/**
 * Created by Srikant Karnani on 26/7/19.
 */
data class GenreResponse(
    val genres: List<Genre>
) : BaseResponse() {
    data class Genre(
        val id: Int,
        val name: String
    )
}

fun GenreResponse?.asDatabaseModel(): Array<Genre> {
    if (this == null)
        return emptyArray()
    return genres.map {
        Genre(id = it.id, name = it.name)
    }.toTypedArray()
}