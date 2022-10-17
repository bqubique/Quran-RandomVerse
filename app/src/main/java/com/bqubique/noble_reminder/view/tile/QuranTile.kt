package com.bqubique.noble_reminder.view.tile

import androidx.wear.tiles.*
import androidx.wear.tiles.DimensionBuilders.dp
import androidx.wear.tiles.DimensionBuilders.expand
import com.bqubique.noble_reminder.api.VerseApi

import com.bqubique.noble_reminder.model.english_verse.EnglishVerseModel
import com.bqubique.noble_reminder.util.TileDrawables.asrPictures
import com.bqubique.noble_reminder.util.TileDrawables.dhuhrPictures
import com.bqubique.noble_reminder.util.TileDrawables.maghribPictures
import com.bqubique.noble_reminder.util.TileDrawables.sunrisePictures
import com.google.android.horologist.tiles.CoroutinesTileService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.util.*
import javax.inject.Inject

private const val ID_IMAGE_START_RUN = "image_start_run"
private const val TAG = "QuranTile"

@AndroidEntryPoint
class QuranTile : CoroutinesTileService() {
    @Inject
    lateinit var verseApi: VerseApi

    private val regex = Regex("[0-9]")

    override suspend fun resourcesRequest(requestParams: RequestBuilders.ResourcesRequest): ResourceBuilders.Resources {
        val drawable = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 5..9 -> sunrisePictures[Random().nextInt(sunrisePictures.size)]
            in 9..14 -> dhuhrPictures[Random().nextInt(dhuhrPictures.size)]
            in 14..17 -> asrPictures[Random().nextInt(asrPictures.size)]
            else -> maghribPictures[Random().nextInt(maghribPictures.size)]
        }

        return ResourceBuilders.Resources.Builder()
            .setVersion("1")
            .addIdToImageMapping(
                ID_IMAGE_START_RUN,
                ResourceBuilders.ImageResource.Builder()
                    .setAndroidResourceByResId(
                        ResourceBuilders.AndroidImageResourceByResId.Builder()
                            .setResourceId(drawable)
                            .build()
                    )
                    .build()
            )
            .build()
    }

    override suspend fun tileRequest(requestParams: RequestBuilders.TileRequest): TileBuilders.Tile {
        return TileBuilders.Tile.Builder()
            .setResourcesVersion("1")
            .setFreshnessIntervalMillis(12000000)
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

    private fun layout(
        ayah: EnglishVerseModel,
        deviceParameters: DeviceParametersBuilders.DeviceParameters
    ): LayoutElementBuilders.Box {

        return LayoutElementBuilders.Box.Builder()
            .setWidth(expand())
            .setHeight(expand())
            .addContent(
                LayoutElementBuilders.Image.Builder()
                    .setWidth(expand())
                    .setHeight(expand())
                    .setResourceId(ID_IMAGE_START_RUN)
                    .setContentScaleMode(LayoutElementBuilders.CONTENT_SCALE_MODE_CROP)
                    .setModifiers(
                        ModifiersBuilders.Modifiers.Builder()
                            .setPadding(
                                ModifiersBuilders.Padding.Builder()
                                    .build()
                            )
                            .setBackground(
                                ModifiersBuilders.Background.Builder()
                                    .setCorner(
                                        ModifiersBuilders.Corner.Builder().setRadius(dp(20f))
                                            .build()
                                    )

                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
            .addContent(
                LayoutElementBuilders.Column.Builder()
                    .addContent(
                        LayoutElementBuilders.Text.Builder().setText(ayah.verse.verseKey)
                            .setFontStyle(
                                LayoutElementBuilders.FontStyle.Builder().build()
                            ).setFontStyle(
                                LayoutElementBuilders.FontStyles.caption1(deviceParameters)
                                    .setWeight(LayoutElementBuilders.FONT_WEIGHT_BOLD).build()
                            ).build()
                    )
                    .addContent(LayoutElementBuilders.Spacer.Builder().setHeight(dp(5F)).build())
                    .addContent(
                        LayoutElementBuilders.Text.Builder()
                            .setText(
                                regex.replace(
                                    Jsoup.parse(ayah.verse.translations[0].text).text(), ""
                                )
                            )
                            .setMaxLines(10)
                            .setFontStyle(
                                LayoutElementBuilders.FontStyles.caption2(deviceParameters)
                                    .setWeight(LayoutElementBuilders.FONT_WEIGHT_BOLD).build()
                            )
                            .setModifiers(
                                ModifiersBuilders.Modifiers.Builder().setPadding(
                                    ModifiersBuilders.Padding.Builder().setAll(dp(10F))
                                        .build()
                                ).build()
                            )
                            .build()
                    )
                    .build()
            )
            .build()

    }


    private fun getVerse(): EnglishVerseModel {
        lateinit var englishVerseResponse: EnglishVerseModel

        do {
            runBlocking {
                CoroutineScope(Dispatchers.IO).launch {
                    englishVerseResponse = verseApi.getRandomEnglishVerse().body()!!
                }.join()
            }
        } while (englishVerseResponse.verse.translations[0].text.length > 150)

        return englishVerseResponse
    }
}

