package com.bqubique.noble_reminder.model.arabic_verse


import com.google.gson.annotations.SerializedName


data class Filters(
    @SerializedName("verse_key")
    val verseKey: String
)