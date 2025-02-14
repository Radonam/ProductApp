package com.example.productsearchapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.example.productsearchapp.R
import com.example.productsearchapp.domain.model.ProductModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

//This object will contain all the composables that will be used in the HomeScreen
object Components {

    @Composable
    fun ProductList(list: List<ProductModel>) {
        LazyVerticalGrid (
            columns = GridCells.Adaptive(120.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            if(list.isNotEmpty()){
                item(span = {
                    GridItemSpan(maxLineSpan)
                }) {
                    ProductItems(
                        modifier = Modifier
                            .width(200.dp)
                            .height(400.dp),
                        product = list.first()
                    )
                }
            }
            if(list.size > 1){
                items(list.subList(1, list.lastIndex)) { product ->
                    ProductItems(
                        modifier = Modifier
                            .width(150.dp)
                            .height(200.dp),
                        product = product
                    )
                }
            }
        }

    }

    @Composable
    fun ProductItems(modifier: Modifier = Modifier,product: ProductModel) {

        Column(modifier = modifier) {

            GlideImage(
                imageModel = { product.imageUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = 10.dp)
            )

            Text(
                modifier = Modifier.padding(start = 5.dp),
                textAlign = TextAlign.Start,
                text = product.title ?: "Unknown",
                color = MaterialTheme.colorScheme.surface,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Spacer(Modifier.height(15.dp))


        }



    }







}