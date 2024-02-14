package com.example.remote.domain

import retrofit2.HttpException
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>,
    ): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }

            return error("${response.code()} ${response.message()}")
        } catch (e: HttpException) {
            return exception(e)
        } catch (e: Throwable) {
            return exception(e)
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(message = "Api call failed $errorMessage")

    private fun <T> exception(e: Throwable): NetworkResult<T> =
        NetworkResult.Exception(e)
}