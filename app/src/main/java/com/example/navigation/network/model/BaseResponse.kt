package com.example.navigation.network.model


import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("status_code")
    val statusCode: Int? = null
    @SerializedName("status_message")
    val statusMessage: String? = null
    val success: Boolean? = null
//
//    @SerializedName("data")
//    val data: T? = null
}