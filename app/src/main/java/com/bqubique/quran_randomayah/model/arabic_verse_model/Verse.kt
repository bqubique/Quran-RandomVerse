package com.bqubique.quran_randomayah.model.arabic_verse_model


import com.google.gson.annotations.SerializedName


data class Verse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text_uthmani")
    val textUthmani: String,
    @SerializedName("verse_key")
    val verseKey: String
)