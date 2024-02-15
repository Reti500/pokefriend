package com.example.mylocations.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.text.MediumDarkText

@Composable
fun LocationCard(
    index: Int,
    lat: Double,
    lng: Double
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                MediumDarkText(
                    modifier = Modifier.weight(2f),
                    text = "$index"
                )
                MediumDarkText(
                    modifier = Modifier.weight(8f),
                    text = "Lat: $lat, Lng: $lng"
                )
            }
        }
    }
}