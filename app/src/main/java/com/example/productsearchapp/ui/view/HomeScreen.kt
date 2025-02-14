package com.example.productsearchapp.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.productsearchapp.ui.intent.HomeScreenEvents
import com.example.productsearchapp.ui.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel


@Preview
@Composable
private fun fvcxv() {
    HomeScreen().OnCreate()
}

//This is the home screen class that will create the home screen
class HomeScreen {

    //In here we could create an onEvent() lambda function to pass events to our viewmodel but
    //since koin can inject our viewmodel in composable params we don't need to do it.
    @Composable
    fun OnCreate(viewModel: HomeScreenViewModel = koinViewModel()) {

        val products = viewModel.homeScreenStates.collectAsStateWithLifecycle()
        val textFieldValue = products.value.search

        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                TextField(
                    value = textFieldValue,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedLabelColor = MaterialTheme.colorScheme.surface,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.surface,
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.surface,
                        unfocusedIndicatorColor = Color.Gray,
                        focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                        focusedContainerColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = MaterialTheme.colorScheme.tertiary
                    ),
                    onValueChange = {
                        viewModel.onEvent(HomeScreenEvents.TextChange(it))
                    },
                    placeholder = { Text("Search Something like (Phone , Dress and etc)") },
                    modifier = Modifier.height(65.dp).fillMaxWidth(),
                    singleLine = true,
                )

            },
            floatingActionButton = {

            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(products.value.error.isEmpty()){
                    if(products.value.isLoading){
                        CircularProgressIndicator()
                    }else{
                        Components.ProductList(
                            list = products.value.products
                        )
                    }
                }else{
                    Text(text = products.value.error)
                }
            }
        }
    }


}