package com.bqubique.noble_reminder.model.arabic_verse


import com.google.gson.annotations.SerializedName


data class Verse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text_uthmani")
    val textUthmani: String,
    @SerializedName("verse_key")
    val verseKey: String
)