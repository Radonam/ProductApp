package com.example.productsearchapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productsearchapp.core.network.ApiStatus
import com.example.productsearchapp.data.remote.mapper.toProductModel
import com.example.productsearchapp.data.repository.ProductRepositoryIMP
import com.example.productsearchapp.domain.model.ProductModel
import com.example.productsearchapp.ui.intent.HomeScreenEvents
import com.example.productsearchapp.ui.intent.HomeScreenState
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val repo: ProductRepositoryIMP) : ViewModel() {


    init {
        //This api called for default data
        loadImages(query = "phone")
    }

    private val _homeScreenStates = MutableStateFlow(HomeScreenState())
    val homeScreenStates = _homeScreenStates.asStateFlow()

    private var searchJob: Job? = null

    //This is the event handler for the HomeScreen
    fun onEvent(event: HomeScreenEvents){
        when(event){
            is HomeScreenEvents.LoadImages -> {
                loadImages(query = event.productName)
            }
            is HomeScreenEvents.TextChange -> {
                updateTextField(text = event.text)
            }
        }
    }

    //This function will update the text state and call the loadImages function
    private fun updateTextField(text:String){
        _homeScreenStates.value = _homeScreenStates.value.copy(
            search = text
        )
        loadImages(text)
    }

    //This function will call the dummy api and update products in the list
    private fun loadImages(query:String){

        //Cancel the previous job if it exists
        searchJob?.cancel()

        searchJob = viewModelScope.launch {

            //As its mentioned in the document the delay between each call should be 100ms
            //I change that to 500ms for better user experience
            delay(500)

            updateLoading(true)

            //Call the dummy api and get the response with flow
            val response = async { repo.searchProducts(query) }.await()

            response.collect {

                when(it.status){
                    //Check the status of the response and update the states accordingly
                    ApiStatus.ERROR -> {
                        updateLoading(false)
                        updateError(it.message.toString())
                    }
                    ApiStatus.LOADING -> {
                        updateLoading(true)
                    }
                    ApiStatus.SUCCESS -> {
                        it.data?.let { data ->

                            val productsList = mutableListOf<ProductModel>()

                            data.products.forEach { product ->
                                productsList.add(product.toProductModel())
                            }

                            updateProducts(productsList)
                            updateLoading(false)
                            updateError("")

                        }

                    }
                }

            }

        }

    }

    //These functions will update the states of the screen separately
    private fun updateProducts(products: List<ProductModel>){
        _homeScreenStates.value = _homeScreenStates.value.copy(
            products = products
        )
    }

    private fun updateLoading(isLoading: Boolean){
        _homeScreenStates.value = _homeScreenStates.value.copy(
            isLoading = isLoading
        )
    }

    private fun updateError(error: String){
        _homeScreenStates.value = _homeScreenStates.value.copy(
            error = error
        )
    }

}