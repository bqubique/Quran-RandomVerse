package com.bqubique.quran_randomayah.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.glance.LocalContext
import androidx.wear.compose.material.*
import com.bqubique.quran_randomayah.R
import com.bqubique.quran_randomayah.theme.WearAppTheme
import dagger.hilt.android.AndroidEntryPoint

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
) {
    WearAppTheme { VerseCardBody() }
}