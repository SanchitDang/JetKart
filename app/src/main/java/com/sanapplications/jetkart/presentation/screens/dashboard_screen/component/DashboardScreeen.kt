package com.sanapplications.jetkart.presentation.screens.dashboard_screen.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
    onItemClick: (Int) -> Unit
) {

    val state = productViewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(color = Color(0xFF4a3298), shape = RoundedCornerShape(10.dp))
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("A Spring Surprise", color = Color.White)
            Text(
                "Cashback 25%",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.flash_icon),
                    contentDescription = "Deals",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Spacer(modifier=Modifier.height(4.dp))
                Text(text = "Deals", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bill_icon),
                    contentDescription = "Bill",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Spacer(modifier=Modifier.height(4.dp))
                Text(text = "Bill", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.game_icon),
                    contentDescription = "Game",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Spacer(modifier=Modifier.height(4.dp))
                Text(text = "Game", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gift_icon),
                    contentDescription = "Gifts",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Spacer(modifier=Modifier.height(4.dp))
                Text(text = "Gifts", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.discover),
                    contentDescription = "More",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Spacer(modifier=Modifier.height(4.dp))
                Text(text = "More", fontSize = 14.sp, textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Special for you", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        //special offer cart
        LazyRow(
            state = popularProductState,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            item {
                ConstraintLayout(
                    modifier = Modifier
                        .width(280.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    //constrains
                    val (bannerText, bannerImage) = createRefs()
                    Image(
                        painter = painterResource(id = R.drawable.image_banner_3),
                        contentDescription = "",
                        modifier = Modifier.constrainAs(bannerImage) {}
                    )
                    Column(
                        modifier = Modifier
                            .background(Color(0x8DB3B0B0))
                            .padding(15.dp)
                            .constrainAs(bannerText) {
                                top.linkTo(bannerImage.top)
                                bottom.linkTo(bannerImage.bottom)
                                start.linkTo(bannerImage.start)
                                end.linkTo(bannerImage.end)
                                height = Dimension.fillToConstraints
                                width = Dimension.fillToConstraints
                            }
                    ) {
                        Text(
                            text = "Fashion",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.heightIn(15.dp))
                        Text(text = "85 Brands", color = Color.White)
                    }
                }
            }
            item {
                //second item
                ConstraintLayout(
                    modifier = Modifier
                        .width(280.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    //constrains
                    val (bannerText2, bannerImage2) = createRefs()
                    Image(
                        painter = painterResource(id = R.drawable.image_banner_2),
                        contentDescription = "",
                        modifier = Modifier.constrainAs(bannerImage2) {}
                    )
                    Column(
                        modifier = Modifier
                            .background(Color(0x8DB3B0B0))
                            .padding(15.dp)
                            .constrainAs(bannerText2) {
                                top.linkTo(bannerImage2.top)
                                bottom.linkTo(bannerImage2.bottom)
                                start.linkTo(bannerImage2.start)
                                end.linkTo(bannerImage2.end)
                                height = Dimension.fillToConstraints
                                width = Dimension.fillToConstraints
                            }
                    ) {
                        Text(
                            text = "Mobile Phone & Gadget",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.heightIn(15.dp))
                        Text(text = "15 Brands", color = Color.White)
                    }
                }
            }

        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Popular Product", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "See More", color = MaterialTheme.colors.TextColor)
        }

        Spacer(modifier = Modifier.height(16.dp))

        //popular product
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