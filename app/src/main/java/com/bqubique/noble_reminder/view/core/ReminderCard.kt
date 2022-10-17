package com.bqubique.noble_reminder.view.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.AppCard

import androidx.wear.compose.material.Text

@Composable
fun ReminderCard(modifier: Modifier, arabicVerse: String, englishVerse: String) {

    //Sometimes some English verses include numeric characters (most probably due to footnotes"
    //filter them out with this Regex object
    val regex = Regex("[0-9]")

    AppCard(modifier = modifier,
        onClick = { /*TODO*/ },
        appName = { Text(arabicVerse) },
        time = {},
        title = { Text(regex.replace(englishVerse, "")) }) {}
}