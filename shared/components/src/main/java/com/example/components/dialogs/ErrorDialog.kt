package com.example.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties

@Composable
fun ErrorDialog(
    title: String,
    description: String,
    onOkText: String,
    dismissOnBack: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    onOk: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = title) },
        text = { Text(text = description) },
        confirmButton = {
            Button(onClick = onOk) {
                Text(text = onOkText)
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBack,
            dismissOnClickOutside = dismissOnClickOutside
        )
    )
}