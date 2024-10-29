package com.sanapplications.jetkart.presentation.screens.dashboard_screen.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanapplications.jetkart.R
import com.sanapplications.jetkart.presentation.common.component.CashBackBanner
import com.sanapplications.jetkart.presentation.common.component.Categories
import com.sanapplications.jetkart.presentation.common.component.OffersSlider
import com.sanapplications.jetkart.presentation.common.component.ProductItem
import com.sanapplications.jetkart.presentation.screens.dashboard_screen.DashboardViewModel
import com.sanapplications.jetkart.presentation.ui.theme.PrimaryColor
import com.sanapplications.jetkart.presentation.ui.theme.PrimaryLightColor
import com.sanapplications.jetkart.presentation.ui.theme.TextColor

@Composable
fun DashboardScreen(
    popularProductState: LazyListState = rememberLazyListState(),
    suggestionProductState: LazyListState = rememberLazyListState(),
    productViewModel: DashboardViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {
    val state = productViewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        // cashback banner
        CashBackBanner()

        Spacer(modifier = Modifier.height(30.dp))

        // categories
        Categories()

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Offers for you", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // offer sliders
        OffersSlider(popularProductState = popularProductState)

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Popular Product", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "See More", color = MaterialTheme.colors.TextColor)
        }

        Spacer(modifier = Modifier.height(16.dp))

        //popular products
        if(state.isLoading) {
            Text(text = "LOADING")
        }
        else {
            LazyRow(
                state = suggestionProductState,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {
                items(state.product!!.size) { index ->
                    val product = state.product[index]
                    ProductItem(
                        product = product,
                        onItemClick = { onItemClick(product.id) },
                        onFavoriteToggle = { productViewModel.toggleFavorite(product.id) }
                    )
                }
            }
        }

    }
}