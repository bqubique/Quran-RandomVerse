package com.bqubique.quran_randomayah.view

import androidx.wear.tiles.*
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.guava.future
import com.bqubique.quran_randomayah.api.QuranApi
import com.bqubique.quran_randomayah.model.ArabicAyah
import com.bqubique.quran_randomayah.model.Ayah
import com.google.common.util.concurrent.Futures
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject


@AndroidEntryPoint
class QuranTile : androidx.wear.tiles.TileService() {
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    @Inject
    lateinit var quranApi: QuranApi

    override fun onTileRequest(
        requestParams: RequestBuilders.TileRequest
    ): ListenableFuture<TileBuilders.Tile> = serviceScope.future {

        TileBuilders.Tile.Builder()
            .setResourcesVersion("1")
            // Creates a timeline to hold one or more tile entries for a specific time periods.
            .setTimeline(
                TimelineBuilders.Timeline.Builder()
                    .addTimelineEntry(
                        TimelineBuilders.TimelineEntry.Builder()
                            .setLayout(
                                LayoutElementBuilders.Layout.Builder()
                                    .setRoot(
                                        layout(getVerse(), requestParams.deviceParameters!!)
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
            .build()
    }

    override fun onResourcesRequest(
        requestParams: RequestBuilders.ResourcesRequest
    ): ListenableFuture<ResourceBuilders.Resources> = Futures.immediateFuture(
        ResourceBuilders.Resources.Builder().setVersion("1").build()
    )


    private fun layout(ayah: String, deviceParameters: DeviceParametersBuilders.DeviceParameters) =
        LayoutElementBuilders.Box.Builder()
            .addContent(
                LayoutElementBuilders.Column.Builder()
                    .addContent(
                        LayoutElementBuilders.Text.Builder()
                            .setText(ayah)
                            .setMaxLines(10)
                            .setFontStyle(
                                LayoutElementBuilders.FontStyles.caption1(deviceParameters).build()
                            )
                            .build()
                    )
                    .build()
            ).build()

    private fun getVerse(): String {
        lateinit var englishVerseResponse: Ayah

        runBlocking {
            CoroutineScope(Dispatchers.IO).launch {
                englishVerseResponse = quranApi.getRandomAyah().body()!!
            }.join()
        }
        return englishVerseResponse.verse.translations[0].text
    }
}