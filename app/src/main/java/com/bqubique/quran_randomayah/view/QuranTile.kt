package com.bqubique.quran_randomayah.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.wear.tiles.GlanceTileService
import androidx.wear.tiles.*
import kotlinx.coroutines.*
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bqubique.quran_randomayah.R
import com.bqubique.quran_randomayah.api.QuranApi
import com.bqubique.quran_randomayah.viewmodel.AyahViewModel
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

class HelloTileService : GlanceTileService() {
    @Composable
    override fun Content() {
        Tile()
    }
}

@Composable
fun Tile (
    ayahViewModel: AyahViewModel = hiltViewModel()
) {

}
