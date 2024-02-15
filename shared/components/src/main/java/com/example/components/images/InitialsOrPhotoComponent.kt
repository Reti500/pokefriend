package com.example.components.images

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.R

@Composable
fun InitialsOrPhotoComponent(
    modifier: Modifier = Modifier,
    name: String,
    source: String,
    backgroundColor: Color = Color.Green,
    initialsColor: Color = Color.Black,
    @DrawableRes placeHolder: Int
) {
    var hasError by remember { mutableStateOf(false) }
    val initials = StringBuilder()

    val names = name.split("\\s+".toRegex())
    for (n in names) {
        if (!n.first().isLetter()) {
            break
        }

        initials.append(n.first().uppercase())

        if (initials.toString().length >= 2) {
            break
        }
    }

    if (source.isEmpty()) {
        InitialsView(
            modifier = modifier,
            fontSize = 64.sp,
            backgroundColor = backgroundColor,
            initials = initials.toString(),
            initialsColor = initialsColor
        )
    } else if (hasError) {
        if (initials.toString().isEmpty()) {
            RemoteCircleImage(
                modifier = modifier,
                source = source,
                placeHolder = placeHolder,
                onError = { hasError = true }
            )
        } else {
            InitialsView(
                modifier = modifier,
                fontSize = 64.sp,
                backgroundColor = backgroundColor,
                initials = initials.toString(),
                initialsColor = initialsColor
            )
        }
    } else {
        RemoteCircleImage(
            modifier = modifier,
            source = source,
            placeHolder = placeHolder,
            onError = { hasError = true }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewSuccessInitials() {
    InitialsOrPhotoComponent(
        modifier = Modifier.size(150.dp),
        name = "Ricardo Gonzalez Tellez",
        source = "",
        placeHolder = R.drawable.profile_1
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewErrorInitials() {
    InitialsOrPhotoComponent(
        modifier = Modifier.size(150.dp),
        name = "%Ricardo Gonzalez Tellez",
        source = "",
        placeHolder = R.drawable.profile_1
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewErrorUrlInitials() {
    InitialsOrPhotoComponent(
        modifier = Modifier.size(150.dp),
        name = "Ricardo Gonzalez Tellez",
        source = "http://ricardo.test",
        placeHolder = R.drawable.profile_1
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewSuccessUrlInitials() {
    InitialsOrPhotoComponent(
        modifier = Modifier.size(150.dp),
        name = "Ricardo Gonzalez Tellez",
        source = "https://marketplace.canva.com/EAFqNrAJpQs/1/0/1600w/canva-neutral-pink-modern-circle-shape-linkedin-profile-picture-WAhofEY5L1U.jpg",
        placeHolder = R.drawable.profile_1
    )
}