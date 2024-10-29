package com.sanapplications.jetkart.presentation.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanapplications.jetkart.R
import com.sanapplications.jetkart.presentation.ui.theme.PrimaryLightColor

@Composable
fun Categories() {
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
            Spacer(modifier= Modifier.height(4.dp))
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
            Spacer(modifier= Modifier.height(4.dp))
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
            Spacer(modifier= Modifier.height(4.dp))
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
            Spacer(modifier= Modifier.height(4.dp))
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
            Spacer(modifier= Modifier.height(4.dp))
            Text(text = "More", fontSize = 14.sp, textAlign = TextAlign.Center)
        }
    }
}