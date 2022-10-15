package com.bqubique.quran_randomayah.model.english_verse_model


import com.google.gson.annotations.SerializedName


data class EnglishVerseModel(
    @SerializedName("verse")
    val verse: Verse
)