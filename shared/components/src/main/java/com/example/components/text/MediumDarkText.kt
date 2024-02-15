package com.example.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun MediumDarkText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 18.sp
) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontWeight = FontWeight.Black,
            fontSize = fontSize
        )
    )
}