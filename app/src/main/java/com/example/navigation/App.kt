
package com.example.navigation

import android.app.Application
import com.example.navigation.common.Toaster
import com.example.navigation.storage.AppPreferences


/**
 * Created by Srikant Karnani on 25/7/19.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
        Toaster.init(this)
    }
}