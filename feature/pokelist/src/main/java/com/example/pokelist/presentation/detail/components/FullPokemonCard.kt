package com.example.pokelist.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.buttons.FavoriteButton
import com.example.components.images.InitialsOrPhotoComponent
import com.example.components.text.MediumDarkText
import com.example.pokelist.R
import com.example.pokelist.domain.models.Pokemon

@Composable
fun FullPokemonCard(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onFavorite: (id: Int, favorite: Boolean) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                FavoriteButton(isFavorite = pokemon.isFavorite) {
                    onFavorite(pokemon.detail?.id ?: 0, !pokemon.isFavorite)
                }
            }

            InitialsOrPhotoComponent(
                modifier = Modifier.size(200.dp).align(Alignment.CenterHorizontally),
                name = pokemon.name,
                source = pokemon.detail?.image ?: "",
                placeHolder = R.drawable.pokemon_1
            )

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))

            MediumDarkText(text = "Id: ${pokemon.detail?.id}")
            MediumDarkText(text = "Name: ${pokemon.name}")
            MediumDarkText(text = "Height: ${pokemon.detail?.height}")
            MediumDarkText(text = "Weight: ${pokemon.detail?.weight}")

            pokemon.detail?.types?.forEachIndexed { k, v ->
                MediumDarkText(text = "Type ${k+1}: $v")
            }
        }
    }
}