package com.example.currencyconverter.common

sealed class Resource<out T>(val data: T? = null, val messages: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error : String) : Resource<T>(messages = error)
    class Loading<T>() : Resource<T>()
}
