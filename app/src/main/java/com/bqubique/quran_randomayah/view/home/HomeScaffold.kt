@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.bqubique.quran_randomayah.view.home

import android.util.Log
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Hearing
import androidx.compose.material.icons.outlined.MenuBook

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.foundation.CurvedTextStyle
import androidx.wear.compose.material.*
import com.bqubique.quran_randomayah.theme.DarkColors

import com.bqubique.quran_randomayah.view.core.ReminderScaffold
import com.bqubique.quran_randomayah.view.hadith.HadithViewModel
import com.bqubique.quran_randomayah.view.verse.VerseViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.jsoup.Jsoup

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScaffold(
    verseViewModel: VerseViewModel = hiltViewModel(),
    hadithViewModel: HadithViewModel = hiltViewModel(),
) {
    val isLoadingVerse = verseViewModel.loading.observeAsState()
    val arabicVerse = verseViewModel.arabicVerse.observeAsState()
    val englishVerse = verseViewModel.englishVerse.observeAsState()

    val isLoadingHadith = hadithViewModel.loading.observeAsState()
    val hadith = hadithViewModel.hadith.observeAsState()

    val pagerState = rememberPagerState()
    val verseScrollState = rememberScalingLazyListState()
    val hadithScrollState = rememberScalingLazyListState()

    if (!isLoadingVerse.value!! && !isLoadingHadith.value!!) {
        Scaffold(
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            timeText = {
                TimeText(
                    startLinearContent = {
                        Text(text = if (pagerState.currentPage == 0) englishVerse.value!!.verse.verseKey else hadith.value?.bookNumber + ":" + hadith.value?.hadithNumber)
                    },
                    startCurvedContent = {
                        curvedText(
                            text = if (pagerState.currentPage == 0) englishVerse.value!!.verse.verseKey else hadith.value?.bookNumber?.replaceFirstChar { c -> c.uppercaseChar() } + ":" + hadith.value?.hadithNumber,
                            style = CurvedTextStyle(color = DarkColors.primary),
                        )
                    },
                )
            },
            positionIndicator = {
                PositionIndicator(scalingLazyListState = if (pagerState.currentPage == 0) verseScrollState else hadithScrollState)
            }
        ) {
            HorizontalPager(count = 2, state = pagerState) { page ->
                if (page == 0) {
                    ReminderScaffold(
                        english = englishVerse.value!!.verse.translations[0].text,
                        arabic = arabicVerse.value!!.verses[0].textUthmani,
                        icon = {
                            Icon(
                                Icons.Outlined.MenuBook,
                                contentDescription = "Book Icon",
                                tint = MaterialTheme.colors.onPrimary,
                            )
                        },
                        scalingLazyListState = verseScrollState,
                        onRefresh = { verseViewModel.getVerse() },
                        isVerse = true,
                    )
                } else {
                    ReminderScaffold(
                        english = Jsoup.parse(hadith.value?.hadith?.get(0)?.body!!).text(),
                        arabic = Jsoup.parse(hadith.value?.hadith?.get(1)?.body!!).text(),
                        icon = {
                            Icon(
                                Icons.Outlined.Hearing,
                                contentDescription = "Hearing Icon",
                                tint = MaterialTheme.colors.onPrimary,
                            )
                        },
                        scalingLazyListState = hadithScrollState,
                        onRefresh = { hadithViewModel.getHadith() },
                        isVerse = false,

                    )
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LoadingAnimation(
                isVerse = pagerState.currentPage == 0
            )
        }
    }
}

