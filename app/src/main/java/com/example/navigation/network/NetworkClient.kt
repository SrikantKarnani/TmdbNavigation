package com.example.navigation.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Srikant Karnani on 26/7/19.
 */
object NetworkClient {
    private lateinit var webService: TmdbService
    private var BASE_URL = "https://api.themoviedb.org/3/"
    private var API_KEY = "15ec94eb5e444e9b5b5c315ebd0d608"
    fun getWebService(): TmdbService {
        if (!::webService.isInitialized) {
            val requestIntercepter = Interceptor { chain ->
                val url = chain.request()
                    .url.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client =
                OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                    .addInterceptor(requestIntercepter)
                    .build()
            val builder = Retrofit.Builder()
            builder.client(client)
            builder.addConverterFactory(GsonConverterFactory.create())
            builder.baseUrl(BASE_URL)
            webService = builder.build().create(TmdbService::class.java)
        }
        return webService
    }
}