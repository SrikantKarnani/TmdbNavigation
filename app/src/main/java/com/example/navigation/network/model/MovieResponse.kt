package com.example.navigation.network.model


import com.example.navigation.storage.db.models.*
import com.google.gson.annotations.SerializedName

/**
 * Created by Srikant Karnani on 26/7/19.
 */
data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) : BaseResponse() {
    data class Movie(
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,

        @SerializedName("genre_ids")
        var genreIds: ArrayList<Int>,

        val id: Int,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
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

fun MovieResponse?.asDatabaseModel(): Array<Movie> {
    if (this == null) {
        return emptyArray()
    }
    return results.map {
        Movie(
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

fun MovieResponse?.asTrendingDatabaseModel(): Array<TrendingMovie> {
    if (this == null) {
        return emptyArray()
    }
    return results.sortedBy { movie ->
        movie.popularity
    }.map {
        TrendingMovie(it.id)
    }.toTypedArray()
}

fun MovieResponse?.asPopularDatabaseModel(): Array<PopularMovie> {
    if (this == null) {
        return emptyArray()
    }
    return results.sortedBy { movie ->
        movie.popularity
    }.map {
        PopularMovie(it.id)
    }.toTypedArray()
}

fun MovieResponse?.asUpcomingDatabaseModel(): Array<UpcomingMovies> {
    if (this == null) {
        return emptyArray()
    }
    return results.sortedBy { movie ->
        movie.popularity
    }.map {
        UpcomingMovies(it.id)
    }.toTypedArray()
}

fun MovieResponse?.asNowPlayingDatabaseModel(): Array<NowPlayingMovie> {
    if (this == null) {
        return emptyArray()
    }
    return results.sortedBy { movie ->
        movie.popularity
    }.map {
        NowPlayingMovie(it.id)
    }.toTypedArray()
}