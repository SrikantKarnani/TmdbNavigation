package com.example.navigation.network.model


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    data class Result(
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("first_air_date")
        val firstAirDate: String,
        @SerializedName("genre_ids")
        val genreIds: ArrayList<Int>,
        val id: Int,
        @SerializedName("known_for")
        val knownFor: List<Any>,
        @SerializedName("media_type")
        val mediaType: String,
        val name: String,
        @SerializedName("origin_country")
        val originCountry: ArrayList<String>,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("original_title")
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("profile_path")
        val profilePath: String?,
        @SerializedName("release_date")
        val releaseDate: String,
        val title: String,
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}

fun SearchResponse?.asDatabaseMovieModel(): Array<com.example.navigation.storage.db.models.Movie> {
    if (this == null) {
        return emptyArray()
    }
    return results.filter { it.mediaType == "movie" }.map {
        com.example.navigation.storage.db.models.Movie(
            adult = it.adult,
            backdropPath = it.backdropPath,
            genreIds = it.genreIds,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            id = it.id,
            video = it.video,
            overview = it.overview,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            popularity = it.popularity,
            releaseDate = it.releaseDate,
            posterPath = it.posterPath,
            title = it.title
        )
    }.toTypedArray()
}

fun SearchResponse?.asDatabaseTvModel(): Array<com.example.navigation.storage.db.models.Tv> {
    if (this == null) {
        return emptyArray()
    }
    return results.filter { it.mediaType == "tv" }.map {
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

fun SearchResponse?.asDatabasePersonModel() {
    if (this == null) {
        return
    }
    return
}