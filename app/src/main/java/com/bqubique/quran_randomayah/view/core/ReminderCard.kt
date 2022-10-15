package com.bqubique.quran_randomayah.view.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.AppCard
import androidx.wear.compose.material.Text

@Composable
fun ReminderCard(modifier: Modifier, arabicVerse: String, englishVerse: String, verseKey: String) {
    return AppCard(modifier = modifier,
        onClick = { /*TODO*/ },
        appName = { Text(arabicVerse) },
        time = { Text(verseKey) },
        title = { Text(englishVerse) }) {}
}