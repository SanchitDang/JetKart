package com.sanapplications.jetkart.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CashBackBanner() {
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
}