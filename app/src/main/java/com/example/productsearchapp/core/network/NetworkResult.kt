package com.example.productsearchapp.core.network

//This class is used to handle the response from the api
sealed class NetworkResult<T>(val status: ApiStatus, val data: T? = null, val message: String? = null) {
    data class Success<T>(val _data: T?): NetworkResult<T>(status = ApiStatus.SUCCESS, data = _data , message = null)
    data class Error<T>(val e: Exception): NetworkResult<T>(status = ApiStatus.ERROR, message = e.message)
    data class Loading<T>(val isLoading: Boolean): NetworkResult<T>(status = ApiStatus.LOADING)
}