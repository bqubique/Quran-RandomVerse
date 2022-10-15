package com.bqubique.quran_randomayah.model.english_verse


import com.google.gson.annotations.SerializedName


data class EnglishVerseModel(
    @SerializedName("verse")
    val verse: Verse
)