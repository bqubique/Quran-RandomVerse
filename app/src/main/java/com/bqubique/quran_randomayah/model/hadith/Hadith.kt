package com.bqubique.quran_randomayah.model.hadith


import com.google.gson.annotations.SerializedName


data class Hadith(
    @SerializedName("body")
    val body: String,
    @SerializedName("chapterNumber")
    val chapterNumber: String,
    @SerializedName("chapterTitle")
    val chapterTitle: String,
    @SerializedName("grades")
    val grades: List<Any>,
    @SerializedName("lang")
    val lang: String,
    @SerializedName("urn")
    val urn: Int
)