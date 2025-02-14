package com.example.productsearchapp

import android.app.Application
import com.example.productsearchapp.core.di.NetworkModule
import com.example.productsearchapp.core.di.ProductModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ProductsApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ProductsApplication)
            androidLogger()
            modules(
                NetworkModule,
                ProductModule
            )
        }
    }
}