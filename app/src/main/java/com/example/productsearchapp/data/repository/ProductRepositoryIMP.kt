package com.example.productsearchapp.data.repository

import com.example.productsearchapp.core.network.NetworkResult
import com.example.productsearchapp.core.network.toResultFlow
import com.example.productsearchapp.data.remote.ApiService
import com.example.productsearchapp.data.remote.dto.ProductsList
import com.example.productsearchapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryIMP(private val api: ApiService): ProductRepository {
    override suspend fun searchProducts(query: String): Flow<NetworkResult<ProductsList>> {
        return toResultFlow() {
            api.searchProducts(query)
        }
    }
}