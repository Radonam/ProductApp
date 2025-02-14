package com.example.productsearchapp.domain.repository

import com.example.productsearchapp.core.network.NetworkResult
import com.example.productsearchapp.data.remote.dto.ProductsList
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun searchProducts(query:String): Flow<NetworkResult<ProductsList>>
}