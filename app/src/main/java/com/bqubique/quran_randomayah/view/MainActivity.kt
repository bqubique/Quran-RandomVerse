package com.bqubique.quran_randomayah.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.*
import com.bqubique.quran_randomayah.theme.WearAppTheme
import com.bqubique.quran_randomayah.viewmodel.AyahViewModel
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun WearApp(
    viewModel: AyahViewModel = hiltViewModel()
) {

    val isLoading = viewModel.loading.observeAsState()
    val arabicVerse = viewModel.arabicVerse.observeAsState()
    val englishVerse = viewModel.englishVerse.observeAsState()

    WearAppTheme {
        Scaffold(
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            timeText = { TimeText() }
        ) {
            Log.d(TAG, "WearApp: ${isLoading.value}")
            if (!isLoading.value!!) {
                ScalingLazyColumn(
                    contentPadding = PaddingValues(
                        top = 20.dp,
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 5.dp
                    )
                ) {
                    item {
                        TitleCard(
                            onClick = { },
                            title = {
                                Text(
                                    arabicVerse.value!!.verses[0].textUthmani,
                                    fontSize = 15.sp
                                )
                            },
                            time = { Text(arabicVerse.value!!.verses[0].verseKey) },
                            content = { Text(englishVerse.value!!.verse.translations[0].text) },
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                    item {
                        Chip(
                            label = { Text("Refresh") },
                            icon = { Icon(Icons.Default.Refresh, contentDescription = "Refresh") },
                            onClick = { viewModel.getVerse() },
                            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
                        )

                    }
                }
            } else {
                Text(
                    "Loading", modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Green)
                )
                Log.d(TAG, "WearApp: ${isLoading.value}")
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    WearApp()
}