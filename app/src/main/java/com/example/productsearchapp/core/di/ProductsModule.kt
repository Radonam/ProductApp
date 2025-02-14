package com.example.productsearchapp.core.di

import com.example.productsearchapp.data.remote.ApiService
import com.example.productsearchapp.domain.repository.ProductRepository
import com.example.productsearchapp.data.repository.ProductRepositoryIMP
import com.example.productsearchapp.ui.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

fun provideService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

val ProductModule = module {
    singleOf(::provideService)
    singleOf(::ProductRepositoryIMP){bind<ProductRepository>()}
    viewModelOf(::HomeScreenViewModel)
}