package com.bqubique.quran_randomayah.model


import com.google.gson.annotations.SerializedName

data class ArabicAyah(
    @SerializedName("verses")
    val verses: List<VerseX>
)