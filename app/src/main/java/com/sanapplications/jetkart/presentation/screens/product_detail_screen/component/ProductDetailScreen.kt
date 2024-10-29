package com.sanapplications.jetkart.presentation.screens.product_detail_screen.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sanapplications.jetkart.R
import com.sanapplications.jetkart.domain.model.ProductModel
import com.sanapplications.jetkart.presentation.ui.theme.PrimaryColor
import com.sanapplications.jetkart.presentation.ui.theme.TextColor

@Composable
fun ProductDetailScreen(
    product: ProductModel?,
    isLoading: Boolean,
    errorMessage: String?,
    popBack: () -> Unit
) {
    val context = LocalContext.current

    // Check if the product is null or loading
    if (isLoading) {
        // Show a loading indicator while fetching data
       Text(text = "LOADING")
    } else {
        // If product is null after loading, display a not found message
        if (product == null) {
            Text("Product not found", color = MaterialTheme.colors.error)
            return // Exit the function early to avoid rendering below UI
        }

        // State variables
        var colorSelected by remember { mutableStateOf(product.colors.last()) }
        var selectedPicture by remember { mutableStateOf(product.images.first()) }
        var quantity by remember { mutableStateOf(1) }

        // Main UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0x8DB3B0B0)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = popBack,
                    modifier = Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .clip(CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = null
                    )
                }
                Row(
                    modifier = Modifier
                        .width(70.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(3.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.rating.toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Image(
                        painter = painterResource(id = R.drawable.star_icon),
                        contentDescription = null
                    )
                }
            }

            // Product image
            Image(
                painter = rememberAsyncImagePainter(selectedPicture),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )

            // Thumbnail images
            LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                items(product.images.size) { index ->
                    IconButton(
                        onClick = { selectedPicture = product.images[index] },
                        modifier = Modifier
                            .size(50.dp)
                            .border(
                                width = 1.dp,
                                color = if (selectedPicture == product.images[index]) MaterialTheme.colors.PrimaryColor else Color.Transparent,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .background(Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(5.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(product.images[index]),
                            contentDescription = null,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(15.dp)
                    ) {
                        Text(
                            text = product.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        Text(
                            text = product.description,
                            fontSize = 16.sp,
                            color = MaterialTheme.colors.TextColor
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "See more Details",
                                color = MaterialTheme.colors.PrimaryColor,
                                fontSize = 16.sp,
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_right),
                                contentDescription = "",
                                tint = MaterialTheme.colors.PrimaryColor
                            )
                        }
                    }
                    IconButton(onClick = { /* TODO: Handle favorite toggle */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.heart_icon_2),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.Red),
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    Color(0x75F44336),
                                    shape = RoundedCornerShape(
                                        topStart = 20.dp,
                                        bottomStart = 20.dp
                                    )
                                )
                                .padding(10.dp)
                                .weight(1f)
                        )
                    }
                }

                // Color selection
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0x8DB3B0B0),
                            shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                        )
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        items(product.colors.size) { index ->
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .border(
                                        width = 1.dp,
                                        color = if (colorSelected == product.colors[index]) MaterialTheme.colors.PrimaryColor else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .padding(5.dp)
                                    .background( Color(product.colors[index].removePrefix("0x").toLong(16)), shape = CircleShape)
                                    .clip(CircleShape)
                                    .clickable {
                                        colorSelected = product.colors[index]
                                    }
                            )
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = { if (quantity > 1) quantity-- },
                            modifier = Modifier
                                .background(color = Color.White, shape = CircleShape)
                                .clip(CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.remove),
                                contentDescription = null
                            )
                        }
                        Text(
                            text = quantity.toString(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .width(35.dp)
                                .wrapContentHeight()
                        )
                        IconButton(
                            onClick = {
                                if (quantity < 5) {
                                    quantity++
                                } else {
                                    Toast.makeText(context, "You can add a maximum of 5 items at a time.", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier
                                .background(color = Color.White, shape = CircleShape)
                                .clip(CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.plus_icon),
                                contentDescription = null
                            )
                        }
                    }
                }

                // Add to cart button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                        )
                        .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.PrimaryColor,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .width(200.dp)
                            .padding(top = 30.dp, bottom = 30.dp)
                            .height(60.dp)
                            .clip(RoundedCornerShape(15.dp)),
                        onClick = {
                            Toast.makeText(context, "Successfully added to cart", Toast.LENGTH_SHORT).show()
                        },
                    ) {
                        Text(text = "Add to Cart", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}