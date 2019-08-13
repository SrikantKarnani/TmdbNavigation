package com.example.navigation.network


sealed class Result<out T : Any> {
    class Success<out T : Any>(val data: T) : Result<T>()
    class Error(private val exception: Throwable, val message: String = exception.message.orEmpty()) : Result<Nothing>()
}