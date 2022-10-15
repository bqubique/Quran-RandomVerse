package com.bqubique.quran_randomayah.view.verse

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.foundation.CurvedTextStyle
import androidx.wear.compose.material.*
import com.bqubique.quran_randomayah.theme.DarkColors
import com.bqubique.quran_randomayah.theme.LightColors

import com.bqubique.quran_randomayah.view.core.ReminderCard

@Composable
fun VerseScaffold(
    viewModel: VerseViewModel = hiltViewModel()
) {
    val isLoading = viewModel.loading.observeAsState()
    val arabicVerse = viewModel.arabicVerse.observeAsState()
    val englishVerse = viewModel.englishVerse.observeAsState()

    if (!isLoading.value!!) {
        Scaffold(
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            timeText = {
                TimeText(startLinearContent = {
                    Text(
                        text = englishVerse.value!!.verse.verseKey,
                        style = TextStyle(color = MaterialTheme.colors.primary)
                    )
                }, startCurvedContent = {
                    curvedText(
                        text = englishVerse.value!!.verse.verseKey,
                        style = CurvedTextStyle(color = DarkColors.primary )
                    )
                })
            },
        ) {
            ScalingLazyColumn(contentPadding = PaddingValues(
                top = 40.dp, start = 10.dp, end = 10.dp, bottom = 30.dp
            ), content = {
                item {
                    ReminderCard(
                        modifier = Modifier,
                        arabicVerse = arabicVerse.value!!.verses[0].textUthmani,
                        englishVerse = englishVerse.value!!.verse.translations[0].text,
                        verseKey = englishVerse.value!!.verse.verseKey,
                    )
                }
                item {
                    Button(
                        onClick = {
                            viewModel.getVerse()
                        }, modifier = Modifier.padding(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Refresh Verse"
                        )
                    }
                }
            })
        }
    } else {
        Scaffold(
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            timeText = { TimeText() },
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

    }
}



