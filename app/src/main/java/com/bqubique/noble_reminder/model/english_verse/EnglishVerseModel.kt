package com.bqubique.noble_reminder.model.english_verse


import com.google.gson.annotations.SerializedName


data class EnglishVerseModel(
    @SerializedName("verse")
    val verse: Verse
)