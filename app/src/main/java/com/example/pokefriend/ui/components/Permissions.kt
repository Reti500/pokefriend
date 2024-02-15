package com.example.pokefriend.ui.components

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.components.dialogs.ErrorDialog
import com.example.core.injections.openSettings
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permissions() {
    val context = LocalContext.current
    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    var showLocationDialog by remember { mutableStateOf(true) }

    if (showLocationDialog) {
        ErrorDialog(
            title = "Permisos de ubicación",
            description = "Necesitamos que nos des permiso a tu ubicación para poder continuar.",
            onOkText = "Dar permiso",
            dismissOnBack = true,
            dismissOnClickOutside = true,
            onOk = {
                if (locationPermissions.shouldShowRationale) {
                    locationPermissions.launchMultiplePermissionRequest()
                } else {
                    context.openSettings()
                }

                showLocationDialog = false
            },
            onDismissRequest = {
                showLocationDialog = false
            }
        )
    }
}