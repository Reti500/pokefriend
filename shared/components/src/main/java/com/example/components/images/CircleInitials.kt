package com.example.components.images

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InitialsView(
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    backgroundColor: Color,
    initials: String,
    initialsColor: Color
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            color = initialsColor,
            style = TextStyle(
                fontSize = fontSize
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewInitials() {
    InitialsView(
        modifier = Modifier.size(150.dp),
        fontSize = 42.sp,
        backgroundColor = Color.Red,
        initials = "RAGT",
        initialsColor = Color.Black
    )
}