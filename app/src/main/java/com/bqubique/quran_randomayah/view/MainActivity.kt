package com.bqubique.quran_randomayah.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.AppCard
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.bqubique.quran_randomayah.R
import com.bqubique.quran_randomayah.theme.WearAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    WearAppTheme {
        ScalingLazyColumn() {
            item {
                AppCard(
                    onClick = { /*TODO*/ },
                    appName = { Text("Hello") },
                    time = { },
                    title = { Text("Whatever") }) {
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    WearApp()
}