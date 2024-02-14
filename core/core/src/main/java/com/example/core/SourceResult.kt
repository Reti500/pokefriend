package com.example.core

sealed class SourceResult<T>(
    val data: T? = null,
    val message: String? = null,
    val exception: Throwable? = null
) {
    class Success<T>(data: T): SourceResult<T>(data)
    class Error<T>(data: T? = null, message: String?): SourceResult<T>(data, message)
    class OnLoading<T>: SourceResult<T>()
}