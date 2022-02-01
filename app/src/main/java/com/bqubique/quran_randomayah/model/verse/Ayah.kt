package com.bqubique.quran_randomayah.model.verse


import com.google.gson.annotations.SerializedName

data class Ayah(
    @SerializedName("verse")
    val verse: Verse
)