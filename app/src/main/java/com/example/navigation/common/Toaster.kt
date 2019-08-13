package com.example.navigation.common

import android.content.Context
import android.widget.Toast

/**
 * Created by Srikant Karnani on 5/8/19.
 */
object Toaster {

    lateinit var toast: Toast
    fun init(context: Context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
    }


    fun show(msg:String){
        if(::toast.isInitialized){
            toast.setText(msg)
            toast.show()
        }
    }
}