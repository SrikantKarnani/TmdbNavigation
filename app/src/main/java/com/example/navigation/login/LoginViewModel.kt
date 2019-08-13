package com.example.navigation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var mLoginStatus : MutableLiveData<Boolean> = MutableLiveData()

    fun login(username:String, password:String){
        mLoginStatus.value = LoginRepository.checkLogin(username, password)
    }

    fun getLoginStatus():LiveData<Boolean>{
        return mLoginStatus
    }

    fun checkAlreadyLogged():Boolean{
        return LoginRepository.checkAlreadyLoggedIn()
    }
}
