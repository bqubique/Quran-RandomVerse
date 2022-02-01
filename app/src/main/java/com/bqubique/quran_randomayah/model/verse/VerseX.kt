package com.bqubique.quran_randomayah.model.verse


import com.google.gson.annotations.SerializedName

data class VerseX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text_uthmani")
    val textUthmani: String,
    @SerializedName("verse_key")
    val verseKey: String
)