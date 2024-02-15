package com.example.pokefriend.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.text.MediumDarkText
import com.example.pokefriend.data.BottomBarItem

@Composable
fun BottomAppBarItem(
    modifier: Modifier = Modifier,
    item: BottomBarItem,
    selectedItem: String,
    onSelectedItem: (BottomBarItem) -> Unit
) {
    val isSelected = selectedItem == item.name

    Column(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { onSelectedItem(item) }
        ) {
            Icon(
                imageVector =
                if (isSelected) item.selectedIcon
                else item.unSelectedIcon,
                contentDescription = item.name
            )
        }
        if (isSelected) {
            MediumDarkText(text = stringResource(id = item.label), fontSize = 12.sp)
        } else {
            Text(text = stringResource(id = item.label), fontSize = 12.sp)
        }
    }
}