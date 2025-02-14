package com.example.productsearchapp.data.remote

import com.example.productsearchapp.data.remote.dto.ProductsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun searchProducts(
        @Query("q") query:String
    ): Response<ProductsList>

}