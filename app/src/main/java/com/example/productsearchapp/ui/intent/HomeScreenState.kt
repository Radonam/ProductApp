package com.example.productsearchapp.ui.intent

import com.example.productsearchapp.domain.model.ProductModel

data class HomeScreenState(
    val isLoading: Boolean = false,
    val products: List<ProductModel> = emptyList(),
    val error: String = "",
    val search: String = ""
)
