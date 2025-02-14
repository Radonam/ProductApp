package com.example.productsearchapp.core.network

import com.example.productsearchapp.core.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Response


//This function will check the internet connection and convert the retrofit response to a flow of NetworkResult
inline fun <reified T> toResultFlow(crossinline  networkCall: suspend () -> Response<T>): Flow<NetworkResult<T>> =
    flow {
        val internet: Internet by inject(Internet::class.java)
        val isInternetConnected = internet.hasInternetConnection()
        if(isInternetConnected){
            emit(NetworkResult.Loading(true))
            try{
                val response = networkCall()
                if(response.isSuccessful && response.body() != null){
                    emit(NetworkResult.Success(response.body()))
                } else {
                    emit(NetworkResult.Error(Exception(response.message())))
                }
            }catch (e: Exception){
                emit(NetworkResult.Error(Exception(e.toString())))
            }
        } else {
            emit(NetworkResult.Error(Exception(Constants.NO_INTERNET_MESSAGE)))
        }
    }.flowOn(Dispatchers.IO)