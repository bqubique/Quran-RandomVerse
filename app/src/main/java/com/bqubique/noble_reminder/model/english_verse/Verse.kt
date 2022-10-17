package com.bqubique.noble_reminder.model.english_verse


import com.google.gson.annotations.SerializedName


data class Verse(
    @SerializedName("hizb_number")
    val hizbNumber: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("juz_number")
    val juzNumber: Int,
    @SerializedName("manzil_number")
    val manzilNumber: Int,
    @SerializedName("page_number")
    val pageNumber: Int,
    @SerializedName("rub_el_hizb_number")
    val rubElHizbNumber: Int,
    @SerializedName("ruku_number")
    val rukuNumber: Int,
    @SerializedName("sajdah_number")
    val sajdahNumber: Any,
    @SerializedName("translations")
    val translations: List<Translation>,
    @SerializedName("verse_key")
    val verseKey: String,
    @SerializedName("verse_number")
    val verseNumber: Int
)