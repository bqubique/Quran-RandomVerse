package com.bqubique.quran_randomayah.model.arabic_verse


import com.google.gson.annotations.SerializedName


data class Meta(
    @SerializedName("filters")
    val filters: Filters
)