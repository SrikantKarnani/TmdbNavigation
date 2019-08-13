package com.example.navigation.network

import com.example.navigation.network.model.*
import org.intellij.lang.annotations.Language
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Srikant Karnani on 25/7/19.
 */
interface TmdbService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(): Response<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("language") language :String ="en-US", @Query("region") region :String ="US"): Response<MovieResponse>

    @GET("discover/movie")
    suspend fun getNowPlayingMovies(@Query("primary_release_date.gte") startDate:String, @Query("primary_release_date.lte") endDate:String, @Query("region") region :String ="IN"): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("language") language :String ="en-US", @Query("region") region :String?="IN"): Response<MovieResponse>

    @GET("genre/movie/list")
    suspend fun getGenre(): Response<GenreResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") movidId:Int, @Query("append_to_response") response :String ="credits"): Response<MovieDetailsResponse>

    @GET("trending/tv/week")
    suspend fun getTrendingTv(): Response<TvResponse>

    @GET("movie/{id}/images")
    suspend fun getImagesOfMovie(@Path("id") id:Int, @Query("language") language :String ="en-US" , @Query("include_image_language") imageLanguage: String="null") : Response<ImageResponse>

    @GET("search/multi")
    suspend fun getSearchResults(@Query("query") queryString:String):Response<SearchResponse>
}