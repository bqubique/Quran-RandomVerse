package com.bqubique.quran_randomayah.model


import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("id")
    val id: Int,
    @SerializedName("resource_id")
    val resourceId: Int,
    @SerializedName("text")
    val text: String
)