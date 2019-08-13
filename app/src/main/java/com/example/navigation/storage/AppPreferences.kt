package com.example.navigation.storage

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Srikant Karnani on 25/7/19.
 */
object AppPreferences {
    private const val NAME = "TmdbPref"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val IS_FIRST_RUN_PREF = Pair("is_first_run", true)
    private val IS_DARK_THEME = Pair("dark_theme", false)
    private val LAST_DOWNLOADED = Pair("last_downloaded", "")


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firstRun: Boolean
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getBoolean(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)

        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putBoolean(IS_FIRST_RUN_PREF.first, value)
        }

    var darkTheme: Boolean
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getBoolean(IS_DARK_THEME.first, IS_DARK_THEME.second)

        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putBoolean(IS_DARK_THEME.first, value)
        }

    var lastDownloaded: String
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getString(LAST_DOWNLOADED.first, LAST_DOWNLOADED.second)!!

        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putString(LAST_DOWNLOADED.first, value)
        }

}