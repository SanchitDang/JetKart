package com.sanapplications.jetkart.presentation.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sanapplications.jetkart.R
import com.sanapplications.jetkart.domain.model.ProductModel
import com.sanapplications.jetkart.presentation.ui.theme.PrimaryColor
import com.sanapplications.jetkart.presentation.ui.theme.PrimaryLightColor

@Composable
fun ProductItem(
    product: ProductModel,
    onItemClick: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    onItemClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = product.images[0]),
                contentDescription = product.description
            )
        }
        Text(
            text = product.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(150.dp)
        )
        Row(
            modifier = Modifier
                .width(150.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$ ${product.price}",
                fontWeight = FontWeight(600),
                color = MaterialTheme.colors.PrimaryColor
            )
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(
                        MaterialTheme.colors.PrimaryLightColor,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable {
                        onFavoriteToggle()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(
                        id = if (product.isFavourite)
                            R.drawable.heart_icon_2
                        else R.drawable.heart_icon
                    ),
                    contentDescription = "Favourite Icon",
                    modifier = Modifier.padding(3.dp),
                    colorFilter = if (product.isFavourite) ColorFilter.tint(
                        Color.Red
                    ) else null
                )
            }
        }
    }
}