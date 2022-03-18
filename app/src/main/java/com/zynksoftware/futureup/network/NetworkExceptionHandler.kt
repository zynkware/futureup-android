package com.zynksoftware.futureup.network

import android.util.Log
import retrofit2.HttpException

class NetworkExceptionHandler() {

    fun <T> map(throwable: Throwable) : Resource<T> {
        Log.e(TAG, "", throwable)
        return when (throwable) {
            is HttpException -> mapHttpException(throwable)
            is NetworkNotAvailableException -> {
                Resource.Error(message = "No network available")
            }
            else -> {
                Resource.Error(message = "Something went wrong")
            }
        }
    }

    private fun <T> mapHttpException(exception: HttpException): Resource<T> {
        return try {
            val httpCode = exception.code()
            val errorBody = exception.response()?.errorBody()?.string()
            if (errorBody != null) {
                Resource.Error(message = errorBody, httpCode = httpCode)
            } else {
                Resource.Error(message = "Something went wrong", httpCode = httpCode)
            }
        } catch (parseException: Exception) {
            val httpCode = exception.code()
            Resource.Error(message = "Something went wrong", httpCode = httpCode)
        }
    }

    companion object {
        private val TAG = NetworkExceptionHandler::class.simpleName
    }
}