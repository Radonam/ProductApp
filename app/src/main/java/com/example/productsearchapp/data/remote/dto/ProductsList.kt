package com.example.productsearchapp.data.remote.dto

data class ProductsList(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)