package com.bqubique.quran_randomayah.view.tile
//
//import androidx.core.content.ContextCompat
//import androidx.wear.tiles.*
//import androidx.wear.tiles.ColorBuilders.argb
//import androidx.wear.tiles.DimensionBuilders.dp
//import androidx.wear.tiles.DimensionBuilders.expand
//import com.bqubique.quran_randomayah.R
//import com.google.common.util.concurrent.ListenableFuture
//import kotlinx.coroutines.guava.future
//import com.bqubique.quran_randomayah.model.verse.Ayah
//import com.bqubique.quran_randomayah.util.TileDrawables.asrPictures
//import com.bqubique.quran_randomayah.util.TileDrawables.dhuhrPictures
//import com.bqubique.quran_randomayah.util.TileDrawables.maghribPictures
//import com.bqubique.quran_randomayah.util.TileDrawables.sunrisePictures
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.*
//import java.util.*
//import javax.inject.Inject
//
//private const val ID_IMAGE_START_RUN = "image_start_run"
//private const val TAG = "QuranTile"
//
//@AndroidEntryPoint
//class QuranTile : androidx.wear.tiles.TileService() {
//    private val serviceJob = Job()
//    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
//
//    @Inject
//    lateinit var quranApi: QuranApi
//
//    override fun onTileRequest(
//        requestParams: RequestBuilders.TileRequest
//    ): ListenableFuture<TileBuilders.Tile> = serviceScope.future {
//        TileBuilders.Tile.Builder()
//            .setResourcesVersion("1")
//            .setTimeline(
//                TimelineBuilders.Timeline.Builder()
//                    .addTimelineEntry(
//                        TimelineBuilders.TimelineEntry.Builder()
//                            .setLayout(
//                                LayoutElementBuilders.Layout.Builder()
//                                    .setRoot(
//                                        layout(getVerse(), requestParams.deviceParameters!!)
//                                    )
//                                    .build()
//                            )
//                            .build()
//                    )
//                    .build()
//            )
//            .build()
//    }
//
//    override fun onResourcesRequest(
//        requestParams: RequestBuilders.ResourcesRequest
//    ): ListenableFuture<ResourceBuilders.Resources> = serviceScope.future {
//
//        val drawable= when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
//            in 5..9 -> sunrisePictures[Random().nextInt(sunrisePictures.size)]
//            in 9..14 -> dhuhrPictures[Random().nextInt(dhuhrPictures.size)]
//            in 14..17 -> asrPictures[Random().nextInt(asrPictures.size)]
//            else -> maghribPictures[Random().nextInt(maghribPictures.size)]
//        }
//
//        ResourceBuilders.Resources.Builder()
//            .setVersion("1")
//            .addIdToImageMapping(
//                ID_IMAGE_START_RUN,
//                ResourceBuilders.ImageResource.Builder()
//                    .setAndroidResourceByResId(
//                        ResourceBuilders.AndroidImageResourceByResId.Builder()
//                            .setResourceId(drawable)
//                            .build()
//                    )
//                    .build()
//            )
//            .build()
//    }
//
//    private fun layout(ayah: Ayah, deviceParameters: DeviceParametersBuilders.DeviceParameters) =
//        LayoutElementBuilders.Box.Builder()
//            .setWidth(expand())
//            .setHeight(expand())
//            .addContent(
//                LayoutElementBuilders.Image.Builder()
//                    .setWidth(expand())
//                    .setHeight(expand())
//                    .setResourceId(ID_IMAGE_START_RUN)
//                    .setContentScaleMode(LayoutElementBuilders.CONTENT_SCALE_MODE_CROP)
//                    .setModifiers(
//                        ModifiersBuilders.Modifiers.Builder()
//                            .setPadding(
//                                ModifiersBuilders.Padding.Builder()
//                                    .build()
//                            )
//                            .setBackground(
//                                ModifiersBuilders.Background.Builder()
//                                    .setCorner(
//                                        ModifiersBuilders.Corner.Builder().setRadius(dp(20f))
//                                            .build()
//                                    )
//                                    .setColor(
//                                        argb(ContextCompat.getColor(this, R.color.primary))
//                                    )
//                                    .build()
//                            )
//                            .build()
//                    )
//                    .build()
//            )
//            .addContent(
//                LayoutElementBuilders.Column.Builder()
//                    .addContent(
//                        LayoutElementBuilders.Text.Builder().setText(ayah.verse.verseKey)
//                            .setFontStyle(
//                                LayoutElementBuilders.FontStyle.Builder().setColor(
//                                    argb(ContextCompat.getColor(this, R.color.primary))
//                                ).build()
//                            ).setFontStyle(
//                                LayoutElementBuilders.FontStyles.caption1(deviceParameters)
//                                    .setWeight(LayoutElementBuilders.FONT_WEIGHT_BOLD).build()
//                            ).build()
//                    )
//                    .addContent(LayoutElementBuilders.Spacer.Builder().setHeight(dp(5F)).build())
//                    .addContent(
//                        LayoutElementBuilders.Text.Builder()
//                            .setText(ayah.verse.translations[0].text)
//                            .setMaxLines(10)
//                            .setFontStyle(
//                                LayoutElementBuilders.FontStyles.caption2(deviceParameters)
//                                    .setWeight(LayoutElementBuilders.FONT_WEIGHT_BOLD).build()
//                            )
//                            .setModifiers(
//                                ModifiersBuilders.Modifiers.Builder().setPadding(
//                                    ModifiersBuilders.Padding.Builder().setAll(dp(10F))
//                                        .build()
//                                ).build()
//                            )
//                            .build()
//                    )
//                    .build()
//            )
//            .build()
//
//
//    private fun getVerse(): Ayah {
//        lateinit var englishVerseResponse: Ayah
//
//        do {
//            runBlocking {
//                CoroutineScope(Dispatchers.IO).launch {
//                    englishVerseResponse = quranApi.getRandomAyah().body()!!
//                }.join()
//            }
//        } while (englishVerseResponse.verse.translations[0].text.length > 150)
//
//        return englishVerseResponse
//    }
//}
//
