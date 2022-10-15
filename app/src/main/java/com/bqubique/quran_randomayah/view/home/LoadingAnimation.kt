package com.bqubique.quran_randomayah.view.home

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Hearing
import androidx.compose.material.icons.twotone.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import com.bqubique.quran_randomayah.R
import com.bqubique.quran_randomayah.theme.DarkColors
import com.bqubique.quran_randomayah.theme.md_theme_light_onPrimaryContainer

@Composable
fun LoadingAnimation(
    isVerse: Boolean = true
) {
    val infiniteTransition = rememberInfiniteTransition()
    val formFactor = stringResource(id = R.string.form_factor)
    val isSquare by remember { mutableStateOf(isSquare(formFactor)) }

    val size by infiniteTransition.animateValue(
        initialValue = 100.dp,
        targetValue = 200.dp,
        animationSpec = infiniteRepeatable(tween(1000), repeatMode = RepeatMode.Reverse),
        typeConverter = Dp.VectorConverter
    )

    val color by infiniteTransition.animateColor(
        initialValue = MaterialTheme.colors.primary,
        targetValue = md_theme_light_onPrimaryContainer,
        animationSpec = infiniteRepeatable(tween(1000), repeatMode = RepeatMode.Reverse),
    )

    val iconColor by infiniteTransition.animateColor(
        initialValue = md_theme_light_onPrimaryContainer,
        targetValue = MaterialTheme.colors.primary,
        animationSpec = infiniteRepeatable(tween(1000), repeatMode = RepeatMode.Reverse),
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .clip(if (isSquare) RoundedCornerShape(30.dp) else CircleShape)
                    .size(size)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {}
            Icon(
                if (isVerse) Icons.TwoTone.MenuBook else Icons.TwoTone.Hearing,
                contentDescription = "Loading ${if (isVerse) "Verse" else "Hadith"} Icon",
                tint = iconColor,
                modifier = Modifier.size(size / 3)
            )
        }
    }
}

private fun isSquare(resource: String): Boolean = resource.contains("Square")