package com.bqubique.quran_randomayah.model.hadith


import com.google.gson.annotations.SerializedName


data class HadithModel(
    @SerializedName("bookNumber")
    val bookNumber: String,
    @SerializedName("chapterId")
    val chapterId: String,
    @SerializedName("collection")
    val collection: String,
    @SerializedName("hadith")
    val hadith: List<Hadith>,
    @SerializedName("hadithNumber")
    val hadithNumber: String
)