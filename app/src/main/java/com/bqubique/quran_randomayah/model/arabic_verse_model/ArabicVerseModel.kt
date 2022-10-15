package com.bqubique.quran_randomayah.model.arabic_verse_model


import com.google.gson.annotations.SerializedName


data class ArabicVerseModel(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("verses")
    val verses: List<Verse>
)