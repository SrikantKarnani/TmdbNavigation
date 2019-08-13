package com.example.navigation.base

import android.util.Log
import com.example.navigation.common.Toaster
import com.example.navigation.network.Result
import com.example.navigation.network.model.BaseResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Created by Srikant Karnani on 5/8/19.
 */
open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): T? {
        val result = apiResult(call)
        var output: T? = null
        when (result) {
            is Result.Success ->
                output = result.data
            is Result.Error -> Toaster.show(result.message)
        }
        return output

    }

    private suspend fun <T : Any> apiResult(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call.invoke()
            Log.e("Response " , response.toString())
            if (response.isSuccessful)
                Result.Success(response.body()!!)
            else {
                Result.Error(IOException("OOps .. Something went wrong due to  ${response.errorBody()}"))
            }
        } catch (e: HttpException) {
            Result.Error(e,"Internet not available !! Cannot refresh Content now.")
        } catch (e: IOException) {
            Result.Error(e)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }
}