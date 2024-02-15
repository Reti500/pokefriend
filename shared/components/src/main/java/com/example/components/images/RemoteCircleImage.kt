package com.example.components.images

import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun RemoteCircleImage(
    modifier: Modifier = Modifier,
    source: String,
    @DrawableRes placeHolder: Int,
    onError: () -> Unit
) {
    AsyncImage(
        modifier = modifier.clip(CircleShape),
        model = ImageRequest.Builder(LocalContext.current)
            .data(source)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = placeHolder),
        contentScale = ContentScale.Crop,
        contentDescription = "profile",
        error = painterResource(id = placeHolder),
        onError = { onError() }
    )
}