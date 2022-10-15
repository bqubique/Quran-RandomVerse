package com.bqubique.quran_randomayah.view.core

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*

@Composable
fun ReminderScaffold(
    english: String,
    arabic: String,
    icon: @Composable () -> Unit,
    onRefresh: () -> Unit,
    scalingLazyListState: ScalingLazyListState,
    isVerse: Boolean = true
) {

    ScalingLazyColumn(contentPadding = PaddingValues(
        top = 40.dp, start = 10.dp, end = 10.dp, bottom = 30.dp
    ),
        state = scalingLazyListState,
        content = {
        item {
            Button(
                onClick = {}, modifier = Modifier.padding(10.dp)
            ) {
                icon()
            }
        }
        item {
            ReminderCard(
                modifier = Modifier,
                arabicVerse = arabic,
                englishVerse = english,
            )
        }
        item {
            Button(
                onClick = {
                    onRefresh()
                }, modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Refresh ${if(isVerse) "Verse" else "Hadith"}",
                )
            }
        }
    })
}