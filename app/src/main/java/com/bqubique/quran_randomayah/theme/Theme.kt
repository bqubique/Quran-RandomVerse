package com.bqubique.quran_randomayah.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Typography

@Composable
fun WearAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = wearColorPalette,
        typography = Typography(),
        // For shapes, we generally recommend using the default Material Wear shapes which are
        // optimized for round and non-round devices.
        content = content
    )
}