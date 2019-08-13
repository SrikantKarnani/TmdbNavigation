package com.example.navigation.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.navigation.storage.db.models.*

/**
 * Created by Srikant Karnani on 25/7/19.
 */
@Database(
    entities = [Movie::class, Genre::class, Tv::class, Poster::class, Backdrops::class, TrendingMovie::class, TrendingTv::class, PopularMovie::class, PopularTv::class, NowPlayingMovie::class,
        UpcomingMovies::class,Cast::class, Crew::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun genreDao(): GenreDao

    abstract fun tvDao(): TvDao

    abstract fun backdropDao(): BackdropDao

    abstract fun posterDao(): PosterDao

    abstract fun trendingMovieDao(): TrendingMovieDao

    abstract fun trendingTvDao(): TrendingTvDao

    abstract fun popularMovieDao(): PopularMovieDao

    abstract fun popularTvDao(): PopularTvDao

    abstract fun upcomingMovieDao(): UpcomingMovieDao

    abstract fun nowPlayingMovieDao(): NowPlayingMovieDao

    abstract fun castDao(): CastDao

    abstract fun crewDao(): CrewDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDb(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "tmdb_database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}