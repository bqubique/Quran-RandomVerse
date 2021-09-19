package com.bqubique.quran_randomayah.model


import com.google.gson.annotations.SerializedName

data class Verse(
    @SerializedName("hizb_number")
    val hizbNumber: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("juz_number")
    val juzNumber: Int,
    @SerializedName("page_number")
    val pageNumber: Int,
    @SerializedName("rub_number")
    val rubNumber: Int,
    @SerializedName("sajdah_number")
    val sajdahNumber: Any,
    @SerializedName("sajdah_type")
    val sajdahType: Any,
    @SerializedName("translations")
    val translations: List<Translation>,
    @SerializedName("verse_key")
    val verseKey: String,
    @SerializedName("verse_number")
    val verseNumber: Int
)