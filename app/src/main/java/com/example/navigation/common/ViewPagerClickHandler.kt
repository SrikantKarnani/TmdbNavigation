package com.example.navigation.common

/**
 * Created by Srikant Karnani on 26/7/19.
 */
interface ViewPagerClickHandler {
    fun onClick(movieId:Int)

    fun onLongClick(position: Int)
}