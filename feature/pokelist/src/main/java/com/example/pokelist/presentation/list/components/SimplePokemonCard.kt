package com.example.pokelist.presentation.list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.buttons.FavoriteButton
import com.example.components.images.InitialsOrPhotoComponent
import com.example.pokelist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimplePokemonCard(
    id: Int,
    name: String,
    image: String,
    isFavorite: Boolean,
    onSelected: (id: Int) -> Unit,
    onToggleFavorite: (id: Int, favorite: Boolean) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        onClick = { onSelected(id) }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InitialsOrPhotoComponent(
                modifier = Modifier.size(120.dp),
                name = name,
                source = image,
                placeHolder = R.drawable.pokemon_1
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(10f)
            ) {
                Text(
                    text = name.capitalize(),
                    style = TextStyle(
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp
                    )
                )
            }

            FavoriteButton(isFavorite = isFavorite) {
                onToggleFavorite(id, !isFavorite)
            }
        }
    }
}