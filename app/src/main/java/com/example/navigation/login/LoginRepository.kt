package com.example.navigation.login

import com.example.navigation.App
import com.example.navigation.storage.AppPreferences

/**
 * Created by Srikant Karnani on 25/7/19.
 */
object LoginRepository {

    fun checkLogin(username:String, password:String):Boolean{
        if(AppPreferences.firstRun){
            AppPreferences.firstRun = false
        }
        return true
    }

    fun checkAlreadyLoggedIn():Boolean{
        return !AppPreferences.firstRun
    }
}