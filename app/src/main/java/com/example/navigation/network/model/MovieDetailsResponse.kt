package com.example.navigation.network.model


import com.example.navigation.storage.db.models.Cast
import com.example.navigation.storage.db.models.Crew
import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val budget: Long,
    val credits: Credits,
    val homepage: Any?,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean
) {
    data class Credits(
        val cast: List<Cast>,
        val crew: List<Crew>
    ) {
        data class Cast(
            @SerializedName("cast_id")
            val castId: Int,
            val character: String,
            @SerializedName("credit_id")
            val creditId: String,
            val gender: Int,
            val id: Int,
            val name: String,
            val order: Int,
            @SerializedName("profile_path")
            val profilePath: String?
        )

        data class Crew(
            @SerializedName("credit_id")
            val creditId: String,
            val department: String,
            val gender: Int,
            val id: Int,
            val job: String,
            val name: String,
            @SerializedName("profile_path")
            val profilePath: String?
        )
    }
}

fun MovieDetailsResponse?.asCastDatabaseModel(): Array<Cast> {
    if (this == null)
        return emptyArray()
    return this.credits.cast.map {
        Cast(this.id, it.castId, it.character, it.creditId, it.gender, it.id, it.name, it.order, it.profilePath)
    }.toTypedArray()
}

fun MovieDetailsResponse?.asCrewDatabaseModel(): Array<Crew> {
    if (this == null)
        return emptyArray()
    return this.credits.crew.map {
        Crew(this.id, it.creditId, it.department, it.gender, it.id, it.job, it.name, it.profilePath)
    }.toTypedArray()
}