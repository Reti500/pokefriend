package com.example.remote.domain

import com.example.core.SourceResult

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null,
    val exception: Throwable? = null
) {
    class Success<T>(data: T): NetworkResult<T>(data)
    class Error<T>(data: T? = null, message: String?): NetworkResult<T>(data, message)
    class Exception<T>(e: Throwable?): NetworkResult<T>(exception = e)
}