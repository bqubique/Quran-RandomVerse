package com.bqubique.quran_randomayah.view

import android.content.Intent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.twotone.CropSquare
import androidx.compose.material.icons.twotone.MenuBook
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.foundation.BasicCurvedText
import androidx.wear.compose.foundation.CurvedTextStyle
import androidx.wear.compose.material.*
import com.bqubique.quran_randomayah.R
import com.bqubique.quran_randomayah.model.ArabicAyah
import com.bqubique.quran_randomayah.model.Ayah
import com.bqubique.quran_randomayah.view.tile.TileRendererActivity
import com.bqubique.quran_randomayah.viewmodel.AyahViewModel

const val TAG = "VerseCard"

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun VerseCardBody(
    viewModel: AyahViewModel = hiltViewModel()
) {
    val lazyScalingLazyListState = rememberScalingLazyListState()
    val isLoading = viewModel.loading.observeAsState()
    val arabicVerse = viewModel.arabicVerse.observeAsState()
    val englishVerse = viewModel.englishVerse.observeAsState()

    if (!isLoading.value!!) {
        Scaffold(
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            timeText = {
                TimeText(
                    leadingLinearContent = {
                        Text(
                            arabicVerse.value?.verses?.get(0)?.verseKey!!,
                            style = TextStyle(color = MaterialTheme.colors.primary)
                        )
                    },
                    leadingCurvedContent = {
                        BasicCurvedText(
                            text = arabicVerse.value?.verses?.get(0)?.verseKey!!,
                            style = CurvedTextStyle(color = MaterialTheme.colors.primary)
                        )
                    })
            },
            positionIndicator = { PositionIndicator(scalingLazyListState = lazyScalingLazyListState) }
        ) {
            ScalingLazyColumn(
                state = lazyScalingLazyListState,
                contentPadding = PaddingValues(
                    top = 20.dp,
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 5.dp
                )
            ) {
                item { VerseCard(arabicVerse = arabicVerse, englishVerse = englishVerse) }
                item { ButtonRefresh(viewModel = viewModel) }
                item { ButtonTile() }
            }
        }
    } else {
        LoadingAnimation()
    }
}

@Composable
fun VerseCard(arabicVerse: State<ArabicAyah?>, englishVerse: State<Ayah?>) {
    TitleCard(
        onClick = { },
        title = {
            Text(
                arabicVerse.value!!.verses[0].textUthmani,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
        time = { Text(arabicVerse.value!!.verses[0].verseKey) },
        content = { Text(englishVerse.value!!.verse.translations[0].text) },
        modifier = Modifier.padding(top = 10.dp)
    )
}

@Composable
fun ButtonRefresh(viewModel: AyahViewModel) {
    Box(contentAlignment = Alignment.Center) {
        Chip(
            label = { Text("Refresh") },
            icon = { Icon(Icons.Default.Refresh, contentDescription = "Refresh") },
            onClick = { viewModel.getVerse(false) },
            modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)
        )
    }
}

@Composable
fun ButtonTile() {
    Box(contentAlignment = Alignment.Center) {
        val context = LocalContext.current
        Chip(
            label = { Text("Quran Tile") },
            icon = { Icon(Icons.TwoTone.CropSquare, contentDescription = "Quran Tile") },
            onClick = {
                context.startActivity(Intent(context, TileRendererActivity::class.java))
            },
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Composable
fun LoadingAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val formFactor = stringResource(id = R.string.form_factor)
    val isRound by remember { mutableStateOf(isRound(formFactor)) }

    val size by infiniteTransition.animateValue(
        initialValue = 100.dp,
        targetValue = 200.dp,
        animationSpec = infiniteRepeatable(tween(1000), repeatMode = RepeatMode.Reverse),
        typeConverter = Dp.VectorConverter
    )

    val color by infiniteTransition.animateColor(
        initialValue = MaterialTheme.colors.primaryVariant,
        targetValue = MaterialTheme.colors.primary,
        animationSpec = infiniteRepeatable(tween(1000), repeatMode = RepeatMode.Reverse),
    )

    val iconColor by infiniteTransition.animateColor(
        initialValue = Color.White,
        targetValue = Color.Black,
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
                    .clip(if (isRound) CircleShape else RoundedCornerShape(30.dp))
                    .size(size)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {}
            Icon(
                Icons.TwoTone.MenuBook,
                contentDescription = "Icon",
                tint = iconColor,
                modifier = Modifier.size(size / 3)
            )
        }
    }
}

private fun isRound(resource: String): Boolean {
    if (resource.contains("Square"))
        return false
    return true
}