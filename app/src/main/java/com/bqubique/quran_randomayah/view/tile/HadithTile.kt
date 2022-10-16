//package com.bqubique.quran_randomayah.view.tile
//
//import androidx.core.content.ContextCompat
//import androidx.wear.tiles.*
//import androidx.wear.tiles.ColorBuilders.argb
//import androidx.wear.tiles.DimensionBuilders.dp
//import androidx.wear.tiles.DimensionBuilders.expand
//import com.bqubique.quran_randomayah.R
//import com.bqubique.quran_randomayah.api.HadithApi
//
//import com.bqubique.quran_randomayah.model.hadith.HadithModel
//import com.bqubique.quran_randomayah.util.TileDrawables.asrPictures
//import com.bqubique.quran_randomayah.util.TileDrawables.dhuhrPictures
//import com.bqubique.quran_randomayah.util.TileDrawables.maghribPictures
//import com.bqubique.quran_randomayah.util.TileDrawables.sunrisePictures
//import com.google.android.horologist.tiles.CoroutinesTileService
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.*
//import org.jsoup.Jsoup
//import java.util.*
//import javax.inject.Inject
//
//private const val ID_IMAGE_START_RUN = "image_start_run"
//private const val TAG = "HadithTile"
//
//@AndroidEntryPoint
//class HadithTile : CoroutinesTileService() {
//    @Inject
//    lateinit var hadithApi: HadithApi
//
//    private val regex = Regex("[0-9]")
//
//    override suspend fun resourcesRequest(requestParams: RequestBuilders.ResourcesRequest): ResourceBuilders.Resources {
//        val drawable = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
//            in 5..9 -> sunrisePictures[Random().nextInt(sunrisePictures.size)]
//            in 9..14 -> dhuhrPictures[Random().nextInt(dhuhrPictures.size)]
//            in 14..17 -> asrPictures[Random().nextInt(asrPictures.size)]
//            else -> maghribPictures[Random().nextInt(maghribPictures.size)]
//        }
//
//        return ResourceBuilders.Resources.Builder().setVersion("1").addIdToImageMapping(
//            ID_IMAGE_START_RUN, ResourceBuilders.ImageResource.Builder().setAndroidResourceByResId(
//                ResourceBuilders.AndroidImageResourceByResId.Builder().setResourceId(drawable)
//                    .build()
//            ).build()
//        ).build()
//    }
//
//    override suspend fun tileRequest(requestParams: RequestBuilders.TileRequest): TileBuilders.Tile {
//        return TileBuilders.Tile.Builder().setResourcesVersion("1").setTimeline(
//            TimelineBuilders.Timeline.Builder().addTimelineEntry(
//                TimelineBuilders.TimelineEntry.Builder().setLayout(
//                    LayoutElementBuilders.Layout.Builder().setRoot(
//                        layout(getHadith(), requestParams.deviceParameters!!)
//                    ).build()
//                ).build()
//            ).build()
//        ).build()
//    }
//
//    private fun layout(
//        hadith: HadithModel, deviceParameters: DeviceParametersBuilders.DeviceParameters
//    ): LayoutElementBuilders.Box {
//
//        return LayoutElementBuilders.Box.Builder().setWidth(expand()).setHeight(expand())
//            .addContent(
//                LayoutElementBuilders.Image.Builder().setWidth(expand()).setHeight(expand())
//                    .setResourceId(ID_IMAGE_START_RUN)
//                    .setContentScaleMode(LayoutElementBuilders.CONTENT_SCALE_MODE_CROP)
//                    .setModifiers(
//                        ModifiersBuilders.Modifiers.Builder().setPadding(
//                            ModifiersBuilders.Padding.Builder().build()
//                        ).setBackground(
//                            ModifiersBuilders.Background.Builder().setCorner(
//                                ModifiersBuilders.Corner.Builder().setRadius(dp(20f)).build()
//                            ).setColor(
//                                argb(ContextCompat.getColor(this, R.color.primary))
//                            ).build()
//                        ).build()
//                    ).build()
//            ).addContent(
//                LayoutElementBuilders.Column.Builder().addContent(
//                    LayoutElementBuilders.Text.Builder()
//                        .setText(hadith.collection + ":" + hadith.hadithNumber).setFontStyle(
//                            LayoutElementBuilders.FontStyle.Builder().setColor(
//                                argb(ContextCompat.getColor(this, R.color.primary))
//                            ).build()
//                        ).setFontStyle(
//                            LayoutElementBuilders.FontStyles.caption1(deviceParameters)
//                                .setWeight(LayoutElementBuilders.FONT_WEIGHT_BOLD).build()
//                        ).build()
//                ).addContent(LayoutElementBuilders.Spacer.Builder().setHeight(dp(5F)).build())
//                    .addContent(
//                        LayoutElementBuilders.Text.Builder().setText(
//                            regex.replace(
//                                Jsoup.parse(
//                                    hadith.hadith[0].body
//                                ).text(), ""
//                            )
//                        ).setMaxLines(10).setFontStyle(
//                            LayoutElementBuilders.FontStyles.caption2(deviceParameters)
//                                .setWeight(LayoutElementBuilders.FONT_WEIGHT_BOLD).build()
//                        ).setModifiers(
//                            ModifiersBuilders.Modifiers.Builder().setPadding(
//                                ModifiersBuilders.Padding.Builder().setAll(dp(10F)).build()
//                            ).build()
//                        ).build()
//                    ).build()
//            ).build()
//
//    }
//
//
//    private fun getHadith(): HadithModel {
//        lateinit var hadithResponse: HadithModel
//
//        do {
//            runBlocking {
//                delay(100)
//                CoroutineScope(Dispatchers.IO).launch {
//                    hadithResponse = hadithApi.getRandomHadith().body()!!
//                }.join()
//            }
//        } while (Jsoup.parse(hadithResponse.hadith[0].body).text().length > 150)
//
//        return hadithResponse
//    }
//}
//
