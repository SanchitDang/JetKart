package com.sanapplications.jetkart.presentation.screens.favourite_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanapplications.jetkart.presentation.screens.dashboard_screen.DashboardViewModel
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.sanapplications.jetkart.presentation.common.component.ProductItem

@Composable
fun FavouriteScreen(
    productViewModel: DashboardViewModel,
    onItemClick: (String) -> Unit
) {
    val state = productViewModel.state.value

    // Filter to get favorite products
    val favoriteProducts = state.product?.filter { it.isFavourite } ?: emptyList()

    Column {
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Your Favorite Products",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Ensure LazyVerticalGrid takes the full available space
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(16.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),

            ) {
                // Display favorite products
                items(favoriteProducts.size) { index ->
                    val product = favoriteProducts[index]
                    ProductItem(
                        product = product,
                        onItemClick = { onItemClick(product.id) },
                        onFavoriteToggle = { productViewModel.toggleFavorite(product.id) }
                    )
                }

                // Optionally, you can add a message when no favorites exist
                if (favoriteProducts.isEmpty()) {
                    item {
                        Text(text = "No favorite products yet", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}