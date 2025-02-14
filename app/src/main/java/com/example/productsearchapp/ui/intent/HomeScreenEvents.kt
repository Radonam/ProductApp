package com.example.productsearchapp.ui.intent

sealed class HomeScreenEvents {
    data class TextChange(val text: String) : HomeScreenEvents()
    data class LoadImages(val productName: String) : HomeScreenEvents()
}