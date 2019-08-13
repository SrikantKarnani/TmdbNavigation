package com.example.navigation.network.model


import com.google.gson.annotations.SerializedName

data class TvResponse(
    val page: Int,
    val results: List<Tv>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) : BaseResponse() {
    data class Tv(
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("first_air_date")
        val firstAirDate: String,
        @SerializedName("genre_ids")
        val genreIds: ArrayList<Int>,
        val id: Int,
        val name: String,
        @SerializedName("origin_country")
        val originCountry: ArrayList<String>,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_name")
        val originalName: String,
        val overview: String,
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}

fun TvResponse?.asDatabaseModel(): Array<com.example.navigation.storage.db.models.Tv> {
    if (this == null) {
        return emptyArray()
    }
    return results.map {
        com.example.navigation.storage.db.models.Tv(
            backdropPath = it.backdropPath,
            genreIds = it.genreIds,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            id = it.id,
            overview = it.overview,
            originalLanguage = it.originalLanguage,
            popularity = it.popularity,
            posterPath = it.posterPath,
            title = it.name,
            releaseDate = it.firstAirDate,
            originalTitle = it.originalName,
            originCountry = it.originCountry
        )
    }.toTypedArray()
}