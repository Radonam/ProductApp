package com.example.productsearchapp.data.remote.mapper

import com.example.productsearchapp.data.remote.dto.Product
import com.example.productsearchapp.domain.model.ProductModel


fun Product.toProductModel():ProductModel{
    return ProductModel(
        title = this.title,
        imageUrl = this.thumbnail
    )
}
